package com.github.kristinjeanna.scedsl;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.github.kristinjeanna.scedsl.AndEvaluator;
import com.github.kristinjeanna.scedsl.BasicEvaluator;
import com.github.kristinjeanna.scedsl.EndsWithEvaluator;
import com.github.kristinjeanna.scedsl.NotEvaluator;

import org.junit.jupiter.api.Test;

public final class EndsWithEvaluatorTest extends AbstractTestBase {

    @Test
    public void testEvaluate() {
        List<String> list = asList("xxYYzz", "aABbcc", "ddeeFF", "mmNnOo");

        // endsWith('yzz')
        EndsWithEvaluator ee1 = new EndsWithEvaluator("yzz");
        assertTrue(ee1.evaluate(list));

        // endsWith('mmnnoo')
        EndsWithEvaluator ee2 = new EndsWithEvaluator("mmnnoo");
        assertTrue(ee2.evaluate(list));

        // endsWith('pp')
        EndsWithEvaluator ee3 = new EndsWithEvaluator("pp");
        assertFalse(ee3.evaluate(list));

        // and('ddeeFF', endsWith('yzz'))
        AndEvaluator ae1 = new AndEvaluator(asList(new BasicEvaluator("ddeeFF"), ee1));
        assertTrue(ae1.evaluate(list));

        // and('ddeeFF', endsWith('pp'))
        AndEvaluator ae2 = new AndEvaluator(asList(new BasicEvaluator("ddeeFF"), ee3));
        assertFalse(ae2.evaluate(list));

        // and('mmnnoo', not(endsWith('pp')))
        AndEvaluator ae3 = new AndEvaluator(asList(new BasicEvaluator("mmnnoo"), new NotEvaluator(ee3)));
        assertTrue(ae3.evaluate(list));
    }
}
