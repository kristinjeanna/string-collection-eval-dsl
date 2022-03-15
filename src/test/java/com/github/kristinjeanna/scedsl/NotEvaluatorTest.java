package com.github.kristinjeanna.scedsl;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.github.kristinjeanna.scedsl.AndEvaluator;
import com.github.kristinjeanna.scedsl.BasicEvaluator;
import com.github.kristinjeanna.scedsl.NotEvaluator;

import org.junit.jupiter.api.Test;

public final class NotEvaluatorTest extends AbstractTestBase {

    @Test
    public void testEvaluate() {
        List<String> list = asList("foo", "bar", "baz", "Bob");

        BasicEvaluator be1 = new BasicEvaluator("foo");
        BasicEvaluator be2 = new BasicEvaluator("XYZ");
        BasicEvaluator be3 = new BasicEvaluator("baz");

        // not('foo')
        NotEvaluator ne1 = new NotEvaluator(be1);
        assertFalse(ne1.evaluate(list));

        // not('XYZ')
        NotEvaluator ne2 = new NotEvaluator(be2);
        assertTrue(ne2.evaluate(list));

        // not(and('foo', 'baz'))
        NotEvaluator ne3 = new NotEvaluator(new AndEvaluator(asList(be1, be3)));
        assertFalse(ne3.evaluate(list));

        // not(and('foo', 'XYZ'))
        NotEvaluator ne4 = new NotEvaluator(new AndEvaluator(asList(be1, be2)));
        assertTrue(ne4.evaluate(list));
    }
}
