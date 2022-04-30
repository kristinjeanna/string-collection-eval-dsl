package com.github.kristinjeanna.scedsl;

import static com.github.kristinjeanna.scedsl.EvaluationUtil.toExpressionEvaluator;
import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.of;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public final class EvaluationUtilTest extends AbstractTestBase {

    static final List<String> samples = unmodifiableList(asList("foo", "Bar", "baz", "ABC"));

    static Stream<Arguments> expressions() {
        return Stream.of( //
                of("'foo'", true), //
                of("'bar'", true), //
                of("'AbC'", true), //
                of("'abz'", false), //
                of("not('XYZ')", true), //
                of("not('FOO')", false), //
                of("and('foo', 'bar')", true), //
                of("and(not('foo'), 'bar')", false), //
                of("and('foo', not('bar'))", false), //
                of("and('baz', 'blag')", false), //
                of("and('baz', not('blag'))", true), //
                of("and('blerg', 'abc')", false), //
                of("and('foo', 'bar', 'abc')", true), //
                of("and('foo', 'bar', 'blag')", false), //
                of("and('foo', not('blerg'), 'abc')", true), //
                of("or('foo', 'bar')", true), //
                of("or('baz', 'blag')", true), //
                of("or('blerg', 'abc')", true), //
                of("or('blerg', 'blag')", false), //
                of("or('blerg', not('blag'))", true), //
                of("or('foo', 'bar', 'blag')", true), //
                of("or(not('blerg'), 'blag')", true), //
                of("and(not('blerg'), or('blag', 'FOO'))", true), //
                of("or(and('bar', 'baz'), not('foo'))", true), //
                of("contains('f')", true), //
                of("contains('x')", false), //
                of("not(contains('x'))", true), //
                of("contains('oo')", true), //
                of("contains('bc')", true), //
                of("and(contains('ba'), contains('oo'))", true), //
                of("and(contains('bz'), contains('oo'))", false), //
                of("or(contains('bc'), contains('oo'))", true), //
                of("startsWith('oo')", false), //
                of("startsWith('fo')", true), //
                of("endsWith('oo')", true), //
                of("endsWith('fo')", false), //
                of("or(startsWith('FO'), endsWith('xx'))", true), //
                of("or(startsWith('xx'), endsWith('bC'))", true), //
                of("or(startsWith('xx'), endsWith('xx'))", false), //
                of("and(startsWith('ab'), endsWith('az'))", true), //
                of("and(startsWith('xx'), endsWith('bc'))", false), //
                of("and(not(startsWith('xx')), endsWith('bc'))", true), //
                of("true", true), //
                of("false", false), //
                of("or(false, false)", false), //
                of("or(false, true)", true), //
                of("or(true, false)", true), //
                of("or(true, true)", true), //
                of("and(false, false)", false), //
                of("and(true, false)", false), //
                of("and(false, true)", false), //
                of("and(true, true)", true), //
                of("not(true)", false), //
                of("not(false)", true), //
                of("xor(false, false)", false), //
                of("xor(true, false)", true), //
                of("xor(false, true)", true), //
                of("xor(true, true)", false), //
                of("xor(false, true, false)", true), //
                of("xor(true, false, true)", false), //
                of("xor(false, 'BAR', false)", true), //
                of("xor(false, false, not('ploof'))", true), //
                of("xor(contains('ar'), false, not('blerg'))", false) //
        );
    }

    @ParameterizedTest(name = "{index}: value=\"{0}\", expected=\"{1}\"")
    @MethodSource("expressions")
    public void testExpressionEvaluation(String expression, boolean expected) {
        Evaluator e = toExpressionEvaluator(expression);
        assertEquals(expected, e.evaluate(samples));
    }
}
