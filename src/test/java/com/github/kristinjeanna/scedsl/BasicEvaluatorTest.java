package com.github.kristinjeanna.scedsl;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import com.github.kristinjeanna.scedsl.BasicEvaluator;
import com.github.kristinjeanna.scedsl.Defaults;

import org.junit.jupiter.api.Test;

public final class BasicEvaluatorTest extends AbstractTestBase {

    @Test
    public void testEvaluate() {
        List<String> list = new ArrayList<>();

        BasicEvaluator be = new BasicEvaluator("foo");
        assertFalse(be.evaluate(list));

        list = asList("foo", "bar", "baz", "Bob");
        assertTrue(be.evaluate(list));

        be = new BasicEvaluator("FOO");
        assertTrue(be.evaluate(list));

        be = new BasicEvaluator("bob");
        assertTrue(be.evaluate(list));

        be = new BasicEvaluator("blag");
        assertFalse(be.evaluate(list));

        Defaults.instance().ignoreCase(false);
        be = new BasicEvaluator("foo");
        assertTrue(be.evaluate(list));
        be = new BasicEvaluator("FOO");
        assertFalse(be.evaluate(list));

        be = new BasicEvaluator("Bob");
        assertTrue(be.evaluate(list));
        be = new BasicEvaluator("bob");
        assertFalse(be.evaluate(list));
    }
}
