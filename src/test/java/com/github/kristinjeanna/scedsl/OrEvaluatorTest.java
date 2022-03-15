package com.github.kristinjeanna.scedsl;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.github.kristinjeanna.scedsl.BasicEvaluator;
import com.github.kristinjeanna.scedsl.NotEvaluator;
import com.github.kristinjeanna.scedsl.OrEvaluator;

import org.junit.jupiter.api.Test;

public final class OrEvaluatorTest extends AbstractTestBase {

    @Test
    public void testEvaluate() {
        List<String> list = asList("foo", "bar", "baz", "Bob");

        BasicEvaluator be1 = new BasicEvaluator("foo");
        BasicEvaluator be2 = new BasicEvaluator("bar");
        BasicEvaluator be3 = new BasicEvaluator("XYZ");
        BasicEvaluator be4 = new BasicEvaluator("ABC");

        // or('foo', 'bar')
        OrEvaluator ae1 = new OrEvaluator(asList(be1, be2));
        assertTrue(ae1.evaluate(list));

        // or('foo', 'XYZ')
        OrEvaluator ae2 = new OrEvaluator(asList(be1, be3));
        assertTrue(ae2.evaluate(list));

        // or('XYZ', 'ABC')
        OrEvaluator ae3 = new OrEvaluator(asList(be3, be4));
        assertFalse(ae3.evaluate(list));

        // or('XYZ', not('ABC'))
        OrEvaluator ae4 = new OrEvaluator(asList(be3, new NotEvaluator(be4)));
        assertTrue(ae4.evaluate(list));
    }
}
