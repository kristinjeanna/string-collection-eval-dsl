package com.github.kristinjeanna.scedsl;

import static com.github.kristinjeanna.scedsl.EvaluationUtil.setupParser;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.kristinjeanna.scedsl.SceDslParser;
import com.github.kristinjeanna.scedsl.SceDslParser.ExpressionContext;
import com.github.kristinjeanna.scedsl.SceDslParser.FunctionContext;
import com.github.kristinjeanna.scedsl.SceDslParser.QuotedStringContext;

public final class ParserTest extends AbstractTestBase {

    static Stream<Arguments> providerFnExpressions() {
        return Stream.of( //
                Arguments.of("or('foo')",
                        "(expression (function (or or ( (expression (quotedString ' (string foo) ')) ))))"), //
                Arguments.of("and('foo')",
                        "(expression (function (and and ( (expression (quotedString ' (string foo) ')) ))))"), //
                Arguments.of("not('foo')",
                        "(expression (function (not not ( (expression (quotedString ' (string foo) ')) ))))"), //
                Arguments.of("and('foo', 'bar')",
                        "(expression (function (and and ( (expression (quotedString ' (string foo) ')) , (expression (quotedString ' (string bar) ')) ))))"), //
                Arguments.of("and(not('foo'), 'bar')",
                        "(expression (function (and and ( (expression (function (not not ( (expression (quotedString ' (string foo) ')) )))) , (expression (quotedString ' (string bar) ')) ))))"), //
                Arguments.of("or('foo', not('bar'))",
                        "(expression (function (or or ( (expression (quotedString ' (string foo) ')) , (expression (function (not not ( (expression (quotedString ' (string bar) ')) )))) ))))"), //
                Arguments.of("and('foo', 'bar', 'baz')",
                        "(expression (function (and and ( (expression (quotedString ' (string foo) ')) , (expression (quotedString ' (string bar) ')) , (expression (quotedString ' (string baz) ')) ))))"), //
                Arguments.of("not(and('foo', 'bar'))",
                        "(expression (function (not not ( (expression (function (and and ( (expression (quotedString ' (string foo) ')) , (expression (quotedString ' (string bar) ')) )))) ))))"), //
                Arguments.of("and('foo', or('bar','baz'), not('blah'))",
                        "(expression (function (and and ( (expression (quotedString ' (string foo) ')) , (expression (function (or or ( (expression (quotedString ' (string bar) ')) , (expression (quotedString ' (string baz) ')) )))) , (expression (function (not not ( (expression (quotedString ' (string blah) ')) )))) ))))") //
        );
    }

    @ParameterizedTest(name = "{index}: value=\"{0}\", expected=\"{1}\"")
    @MethodSource("providerFnExpressions")
    public void testFnExpressionParsing(String expression, String expected) {
        SceDslParser parser = setupParser(expression);
        assertNotNull(parser);

        ExpressionContext expressionCtx = parser.expression();
        assertNotNull(expressionCtx);

        FunctionContext functionCtx = expressionCtx.function();
        QuotedStringContext quotedStringCtx = expressionCtx.quotedString();
        assertAll( //
                () -> assertNotNull(functionCtx), //
                () -> assertNull(quotedStringCtx) //
        );

        String exprString = expressionCtx.toStringTree(parser);
        assertAll( //
                () -> assertNotNull(exprString), //
                () -> assertEquals(expected, exprString) //
        );
    }

    @Test
    public void testInvalidExpression() {
        SceDslParser parser1 = setupParser("foo");
        assertThrows(IllegalStateException.class, () -> parser1.expression());

    }

    @Test
    public void testQsExpressionParsing() {
        final String expression = "'foo'";
        final String expected = "(expression (quotedString ' (string foo) '))";

        SceDslParser parser = setupParser(expression);
        assertNotNull(parser);

        ExpressionContext expressionCtx = parser.expression();
        assertNotNull(expressionCtx);

        FunctionContext functionCtx = expressionCtx.function();
        QuotedStringContext quotedStringCtx = expressionCtx.quotedString();
        assertAll( //
                () -> assertNull(functionCtx), //
                () -> assertNotNull(quotedStringCtx) //
        );

        String exprString = expressionCtx.toStringTree(parser);
        assertAll( //
                () -> assertNotNull(exprString), //
                () -> assertEquals(expected, exprString) //
        );
    }
}
