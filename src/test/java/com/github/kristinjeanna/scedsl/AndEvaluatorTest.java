package com.github.kristinjeanna.scedsl;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.github.kristinjeanna.scedsl.AndEvaluator;
import com.github.kristinjeanna.scedsl.BasicEvaluator;
import com.github.kristinjeanna.scedsl.NotEvaluator;
import com.github.kristinjeanna.scedsl.OrEvaluator;

import org.junit.jupiter.api.Test;

public final class AndEvaluatorTest extends AbstractTestBase {

    @Test
    public void testEvaluate() {
        List<String> list = asList("foo", "bar", "baz", "Bob");

        BasicEvaluator be1 = new BasicEvaluator("foo");
        BasicEvaluator be2 = new BasicEvaluator("bar");
        BasicEvaluator be3 = new BasicEvaluator("XYZ");

        // and('foo', 'bar')
        AndEvaluator ae1 = new AndEvaluator(asList(be1, be2));
        assertTrue(ae1.evaluate(list));

        // and('foo', 'XYZ')
        AndEvaluator ae2 = new AndEvaluator(asList(be1, be3));
        assertFalse(ae2.evaluate(list));

        // and('foo', not('XYZ'))
        AndEvaluator ae3 = new AndEvaluator(asList(be1, new NotEvaluator(be3)));
        assertTrue(ae3.evaluate(list));

        // and('foo', or(not('bar'), not('XYZ')))
        OrEvaluator oe1 = new OrEvaluator(asList(new NotEvaluator(be2), new NotEvaluator(be3)));
        AndEvaluator ae4 = new AndEvaluator(asList(be1, oe1));
        assertTrue(ae4.evaluate(list));
    }
}
