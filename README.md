# string-collection-eval-dsl

[![Build Status](https://github.com/kristinjeanna/string-collection-eval-dsl/actions/workflows/build.yml/badge.svg)](https://github.com/kristinjeanna/string-collection-eval-dsl/actions)

A Java library providing a domain-specific language (DSL) for evaluating the
contents of a string collection.

An example use case involves examining a list of role names possessed by a
user. To assess if the user has the `poweruser` role but not the `admin` role,
a statement of `and('poweruser', not('admin'))` can be evaluated against the list.
The result is a boolean value indicating whether or not the contents of the list
satisfy the statement.

## Evaluators

1. *string* &ndash; evaluates to `true` if *string* is found in the collection;
   `false` otherwise. Always enclosed in single-quotes.

   ```java

    import static com.github.kristinjeanna.scedsl.EvaluationUtil.toExpressionEvaluator;
    import static java.util.Arrays.asList;
    import static java.util.Collections.unmodifiableList;

    public final class Demo {
        public void DemoStringEvaluator() {
            final List<String> list = unmodifiableList(asList("foo", "bar", "baz", "bah"));

            Evaluator evaluator = toExpressionEvaluator("'foo'");
            boolean result = evaluator.evaluate(list); // true

            evaluator = toExpressionEvaluator("'abc'");
            result = evaluator.evaluate(list); // false
        }
    }
   ```

2. **true** &ndash; always evaluates to `true`.

   ```java

    import static com.github.kristinjeanna.scedsl.EvaluationUtil.toExpressionEvaluator;
    import static java.util.Arrays.asList;
    import static java.util.Collections.unmodifiableList;

    public final class Demo {
        public void DemoTrueEvaluator() {
            final List<String> list = unmodifiableList(asList("foo", "bar", "baz", "bah"));

            Evaluator evaluator = toExpressionEvaluator("true");
            boolean result = evaluator.evaluate(list); // true
        }
    }
   ```

3. **false** &ndash; always evaluates to `false`.

   ```java

    import static com.github.kristinjeanna.scedsl.EvaluationUtil.toExpressionEvaluator;
    import static java.util.Arrays.asList;
    import static java.util.Collections.unmodifiableList;

    public final class Demo {
        public void DemoFalseEvaluator() {
            final List<String> list = unmodifiableList(asList("foo", "bar", "baz", "bah"));

            Evaluator evaluator = toExpressionEvaluator("false");
            boolean result = evaluator.evaluate(list); // false
        }
    }
   ```

4. **not()** &ndash; evaluates to the logical inverse of its argument.

   ```java

    import static com.github.kristinjeanna.scedsl.EvaluationUtil.toExpressionEvaluator;
    import static java.util.Arrays.asList;
    import static java.util.Collections.unmodifiableList;

    public final class Demo {
        public void DemoNotEvaluator() {
            final List<String> list = unmodifiableList(asList("foo", "bar", "baz", "bah"));

            Evaluator evaluator = toExpressionEvaluator("not('bah')");
            boolean result = evaluator.evaluate(list); // false

            evaluator = toExpressionEvaluator("not('f00')");
            result = evaluator.evaluate(list); // true

            evaluator = toExpressionEvaluator("not(or('abc','xyz'))");
            result = evaluator.evaluate(list); // true
        }
    }
   ```

5. **and()** &ndash; evaluates to `true` if all evaluators in its argument list evaluate to `true`.

   ```java

    import static com.github.kristinjeanna.scedsl.EvaluationUtil.toExpressionEvaluator;
    import static java.util.Arrays.asList;
    import static java.util.Collections.unmodifiableList;

    public final class Demo {
        public void DemoAndEvaluator() {
            final List<String> list = unmodifiableList(asList("foo", "bar", "baz", "bah"));

            Evaluator evaluator = toExpressionEvaluator("and('foo', 'bah')");
            boolean result = evaluator.evaluate(list); // true

            evaluator = toExpressionEvaluator("and('foo', 'f00')");
            result = evaluator.evaluate(list); // false

            evaluator = toExpressionEvaluator("and('bar', or('zaz', 'bah'))");
            result = evaluator.evaluate(list); // true
        }
    }
   ```

6. **or()** &ndash; evaluates to `true` if at least one evaluator in its argument list evaluates to `true`.

   ```java

    import static com.github.kristinjeanna.scedsl.EvaluationUtil.toExpressionEvaluator;
    import static java.util.Arrays.asList;
    import static java.util.Collections.unmodifiableList;

    public final class Demo {
        public void DemoOrEvaluator() {
            final List<String> list = unmodifiableList(asList("foo", "bar", "baz", "bah"));

            Evaluator evaluator = toExpressionEvaluator("or('foo', 'abc')");
            boolean result = evaluator.evaluate(list); // true

            evaluator = toExpressionEvaluator("or('zaz', not('bah'))");
            result = evaluator.evaluate(list); // false

            evaluator = toExpressionEvaluator("or('bar', and('abc', 'xyz'))");
            result = evaluator.evaluate(list); // true
        }
    }
   ```

7. **xor()** &ndash; evaluates to `true` if exactly one evaluator in its argument list evaluates to `true`.

   ```java

    import static com.github.kristinjeanna.scedsl.EvaluationUtil.toExpressionEvaluator;
    import static java.util.Arrays.asList;
    import static java.util.Collections.unmodifiableList;

    public final class Demo {
        public void DemoXorEvaluator() {
            final List<String> list = unmodifiableList(asList("foo", "bar", "baz", "bah"));

            Evaluator evaluator = toExpressionEvaluator("xor('foo', 'abc')");
            boolean result = evaluator.evaluate(list); // true

            evaluator = toExpressionEvaluator("xor('foo', 'bar')");
            result = evaluator.evaluate(list); // false

            evaluator = toExpressionEvaluator("xor('zaz', not('bah'))");
            result = evaluator.evaluate(list); // false
        }
    }
   ```
