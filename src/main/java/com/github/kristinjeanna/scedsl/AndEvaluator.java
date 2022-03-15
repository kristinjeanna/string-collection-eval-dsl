package com.github.kristinjeanna.scedsl;

import static com.github.kristinjeanna.scedsl.Messages.PARAM_MUST_NOT_BE_NULL;
import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.List;

public final class AndEvaluator extends AbstractMultiEvaluator implements Evaluator {

    public AndEvaluator(List<Evaluator> evaluators) {
        super(evaluators);
    }

    @Override
    public boolean evaluate(Collection<String> stringCollection) {
        requireNonNull(stringCollection, () -> format(PARAM_MUST_NOT_BE_NULL, "stringCollection"));

        boolean result = true;

        for (final Evaluator evaluator : this.evaluators) {
            result &= evaluator.evaluate(stringCollection);
        }

        return result;
    }
}
