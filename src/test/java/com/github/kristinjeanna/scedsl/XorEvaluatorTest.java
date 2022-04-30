package com.github.kristinjeanna.scedsl;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

public final class XorEvaluatorTest extends AbstractTestBase {

    @Test
    public void testEvaluate() {
        List<String> list = asList("foo", "bar", "baz", "Bob");

        BasicEvaluator be1 = new BasicEvaluator("foo");
        BasicEvaluator be2 = new BasicEvaluator("bar");
        BasicEvaluator be3 = new BasicEvaluator("XYZ");
        BasicEvaluator be4 = new BasicEvaluator("ABC");

        // xor('foo', 'bar')
        XorEvaluator xe1 = new XorEvaluator(asList(be1, be2));
        assertFalse(xe1.evaluate(list));

        // xor('foo', 'XYZ')
        XorEvaluator xe2 = new XorEvaluator(asList(be1, be3));
        assertTrue(xe2.evaluate(list));

        // xor('XYZ', 'ABC')
        XorEvaluator xe3 = new XorEvaluator(asList(be3, be4));
        assertFalse(xe3.evaluate(list));

        // xor('XYZ', not('ABC'))
        XorEvaluator xe4 = new XorEvaluator(asList(be3, new NotEvaluator(be4)));
        assertTrue(xe4.evaluate(list));

        // xor(false, false, true, false)
        XorEvaluator xe5 = new XorEvaluator(asList(new BooleanFalseEvaluator(), new BooleanFalseEvaluator(),
                new BooleanTrueEvaluator(), new BooleanFalseEvaluator()));
        assertTrue(xe5.evaluate(list));

        // xor(true, false, true, false)
        XorEvaluator xe6 = new XorEvaluator(asList(new BooleanTrueEvaluator(), new BooleanFalseEvaluator(),
                new BooleanTrueEvaluator(), new BooleanFalseEvaluator()));
        assertFalse(xe6.evaluate(list));
    }
}
