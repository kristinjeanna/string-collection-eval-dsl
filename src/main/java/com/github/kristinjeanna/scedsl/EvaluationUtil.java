package com.github.kristinjeanna.scedsl;

import static com.github.kristinjeanna.scedsl.Messages.PARAM_MUST_NOT_BE_NULL;
import static com.github.kristinjeanna.scedsl.Messages.PARAM_MUST_NOT_BE_NULL_OR_EMPTY;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.nullToEmpty;
import static java.lang.String.format;
import static java.util.Objects.requireNonNull;
import static java.util.logging.Logger.getLogger;
import static org.antlr.v4.runtime.CharStreams.fromString;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import com.github.kristinjeanna.scedsl.SceDslParser.AndContext;
import com.github.kristinjeanna.scedsl.SceDslParser.BooleanFalseContext;
import com.github.kristinjeanna.scedsl.SceDslParser.BooleanTrueContext;
import com.github.kristinjeanna.scedsl.SceDslParser.ContainsContext;
import com.github.kristinjeanna.scedsl.SceDslParser.EndsWithContext;
import com.github.kristinjeanna.scedsl.SceDslParser.ExplicitBooleanContext;
import com.github.kristinjeanna.scedsl.SceDslParser.ExpressionContext;
import com.github.kristinjeanna.scedsl.SceDslParser.FunctionContext;
import com.github.kristinjeanna.scedsl.SceDslParser.NotContext;
import com.github.kristinjeanna.scedsl.SceDslParser.OrContext;
import com.github.kristinjeanna.scedsl.SceDslParser.QuotedStringContext;
import com.github.kristinjeanna.scedsl.SceDslParser.StartsWithContext;
import com.github.kristinjeanna.scedsl.SceDslParser.XorContext;

/**
 * Provides methods for transforming a string-collection-eval DSL expression to an
 * evaluator that can be used to evaluate the expression against a collection of strings.
 */
public final class EvaluationUtil {

    private static final Logger logger = getLogger(EvaluationUtil.class.getName());

    private static Evaluator recurse(ExpressionContext ctx) {
        requireNonNull(ctx, () -> format(PARAM_MUST_NOT_BE_NULL, ctx));

        QuotedStringContext qsCtx = ctx.quotedString();
        if (qsCtx != null) {
            return new BasicEvaluator(qsCtx.string().getText());
        }

        ExplicitBooleanContext boolCtx = ctx.explicitBoolean();
        if (boolCtx != null) {
            BooleanTrueContext trueCtx = boolCtx.booleanTrue();
            BooleanFalseContext falseCtx = boolCtx.booleanFalse();

            if (trueCtx != null) {
                return new BooleanTrueEvaluator();
            }
            else if (falseCtx != null) {
                return new BooleanFalseEvaluator();
            }
        }

        FunctionContext fnCtx = ctx.function();

        NotContext notFnCtx = fnCtx.not();
        AndContext andFnCtx = fnCtx.and();
        OrContext orFnCtx = fnCtx.or();
        XorContext xorFnCtx = fnCtx.xor();
        ContainsContext containsFnCtx = fnCtx.contains();
        StartsWithContext startsWithFnCtx = fnCtx.startsWith();
        EndsWithContext endsWithFnCtx = fnCtx.endsWith();

        Evaluator result = null;
        if (notFnCtx != null) {
            Evaluator innerEvaluator = recurse(notFnCtx.expression());
            result = new NotEvaluator(innerEvaluator);
        }
        else if (andFnCtx != null) {
            List<Evaluator> evaluators = new LinkedList<>();
            for (final ExpressionContext expression : andFnCtx.expression()) {
                Evaluator e = recurse(expression);
                evaluators.add(e);
            }
            result = new AndEvaluator(evaluators);
        }
        else if (orFnCtx != null) {
            List<Evaluator> evaluators = new LinkedList<>();
            for (final ExpressionContext expression : orFnCtx.expression()) {
                Evaluator e = recurse(expression);
                evaluators.add(e);
            }
            result = new OrEvaluator(evaluators);
        }
        else if (xorFnCtx != null) {
            List<Evaluator> evaluators = new LinkedList<>();
            for (final ExpressionContext expression : xorFnCtx.expression()) {
                Evaluator e = recurse(expression);
                evaluators.add(e);
            }
            result = new XorEvaluator(evaluators);
        }
        else if (containsFnCtx != null) {
            String stringFragment = containsFnCtx.quotedString().string().getText();
            result = new ContainsEvaluator(stringFragment);
        }
        else if (startsWithFnCtx != null) {
            String stringFragment = startsWithFnCtx.quotedString().string().getText();
            result = new StartsWithEvaluator(stringFragment);
        }
        else if (endsWithFnCtx != null) {
            String stringFragment = endsWithFnCtx.quotedString().string().getText();
            result = new EndsWithEvaluator(stringFragment);
        }
        return result;
    }

    /**
     * Sets up a {@code SceDslParser} instance that reflects the parsed result of a given
     * expression string.
     *
     * @param expressionString the expression string to use
     * @return a {@code SceDslParser} instance
     */
    public static SceDslParser setupParser(String expressionString) {
        final String es = nullToEmpty(expressionString).trim();
        checkArgument(!es.isEmpty(), PARAM_MUST_NOT_BE_NULL_OR_EMPTY, "expressionString");

        SceDslLexer lexer = new SceDslLexer(fromString(es));
        SceDslParser parser = new SceDslParser(new CommonTokenStream(lexer));

        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
                    int charPositionInLine, String msg, RecognitionException e) {
                throw new IllegalStateException(format("failed to parse at line %s due to: %s", line, msg), e);
            }
        });

        return parser;
    }

    /**
     * Transforms a string-collection-eval DSL expression to an equivalent
     * {@code Evaluator} instance. For most use cases, this is the only method needed to
     * utilize this entire library.
     * <p>
     * Usage example:
     *
     * <pre>
     * List&lt;String&gt; rolesUserOne = Arrays.asList("student", "employee", "alumni");
     * List&lt;String&gt; rolesUserTwo = Arrays.asList("student");
     *
     * Evaluator studentsButNotAlumni = EvaluationUtil.toExpressionEvaluator("and('student', not('alumni'))");
     * boolean result1 = studentsButNotAlumni.evaluate(rolesUserOne)); // result1 equals false
     * boolean result2 = studentsButNotAlumni.evaluate(rolesUserTwo)); // result2 equals true
     * </pre>
     *
     * @param expressionString the expression string to use
     * @return an {@code Evaluator} instance
     */
    public static Evaluator toExpressionEvaluator(String expressionString) {
        final String es = nullToEmpty(expressionString).trim();
        checkArgument(!es.isEmpty(), PARAM_MUST_NOT_BE_NULL_OR_EMPTY, "expressionString");

        SceDslParser parser = setupParser(expressionString);
        ExpressionContext rootExpressionCtx = parser.expression();

        Evaluator evaluator = recurse(rootExpressionCtx);
        logger.fine(() -> format("Expression \"%s\" ==> evaluator \"%s\".", es, evaluator));

        return evaluator;
    }

    private EvaluationUtil() {}
}
