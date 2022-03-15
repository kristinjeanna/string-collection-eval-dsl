package com.github.kristinjeanna.scedsl;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.github.kristinjeanna.scedsl.AndEvaluator;
import com.github.kristinjeanna.scedsl.BasicEvaluator;
import com.github.kristinjeanna.scedsl.Defaults;
import com.github.kristinjeanna.scedsl.NotEvaluator;
import com.github.kristinjeanna.scedsl.StartsWithEvaluator;

import org.junit.jupiter.api.Test;

public final class StartsWithEvaluatorTest extends AbstractTestBase {

    @Test
    public void testEvaluate() {
        Defaults.instance().ignoreCase(true);

        List<String> list = asList("xxYYzz", "aABbcc", "ddeeFF", "mmNnOo");

        // startsWith('aa')
        StartsWithEvaluator se1 = new StartsWithEvaluator("aA");
        assertTrue(se1.evaluate(list));

        // startsWith('XX')
        StartsWithEvaluator se2 = new StartsWithEvaluator("XX");
        assertTrue(se2.evaluate(list));

        // startsWith('pp')
        StartsWithEvaluator se3 = new StartsWithEvaluator("pp");
        assertFalse(se3.evaluate(list));

        // and('ddeeFF', startsWith('aa'))
        AndEvaluator ae1 = new AndEvaluator(asList(new BasicEvaluator("ddeeFF"), se1));
        assertTrue(ae1.evaluate(list));

        // and('ddeeFF', startsWith('pp'))
        AndEvaluator ae2 = new AndEvaluator(asList(new BasicEvaluator("ddeeFF"), se3));
        assertFalse(ae2.evaluate(list));

        // and('mmnnoo', not(startsWith('pp')))
        AndEvaluator ae3 = new AndEvaluator(asList(new BasicEvaluator("mmnnoo"), new NotEvaluator(se3)));
        assertTrue(ae3.evaluate(list));
    }
}
