package com.github.kristinjeanna.scedsl;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.github.kristinjeanna.scedsl.AndEvaluator;
import com.github.kristinjeanna.scedsl.BasicEvaluator;
import com.github.kristinjeanna.scedsl.ContainsEvaluator;
import com.github.kristinjeanna.scedsl.NotEvaluator;

import org.junit.jupiter.api.Test;

public final class ContainsEvaluatorTest extends AbstractTestBase {

    @Test
    public void testEvaluate() {
        List<String> list = asList("xxYYzz", "aABbcc", "ddeeFF", "mmNnOo");

        // contains('bc')
        ContainsEvaluator ce1 = new ContainsEvaluator("bc");
        assertTrue(ce1.evaluate(list));

        // contains('xx')
        ContainsEvaluator ce2 = new ContainsEvaluator("xx");
        assertTrue(ce2.evaluate(list));

        // contains('cd')
        ContainsEvaluator ce3 = new ContainsEvaluator("cd");
        assertFalse(ce3.evaluate(list));

        // and('mmNnOo', contains('aa'))
        AndEvaluator ae1 = new AndEvaluator(asList(new BasicEvaluator("mmNnOo"), ce1));
        assertTrue(ae1.evaluate(list));

        // and('ddeeFF', contains('cd'))
        AndEvaluator ae2 = new AndEvaluator(asList(new BasicEvaluator("ddeeFF"), ce3));
        assertFalse(ae2.evaluate(list));

        // and('mmnnoo', not(contains('cd')))
        AndEvaluator ae3 = new AndEvaluator(asList(new BasicEvaluator("mmnnoo"), new NotEvaluator(ce3)));
        assertTrue(ae3.evaluate(list));
    }
}
