package com.github.kristinjeanna.scedsl;

import static com.github.kristinjeanna.scedsl.Messages.PARAM_MUST_NOT_BE_NULL;
import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

import java.util.Collection;

public final class NotEvaluator implements Evaluator {

    private final Evaluator innerEvaluator;

    public NotEvaluator(Evaluator innerEvaluator) {
        requireNonNull(innerEvaluator, () -> format(PARAM_MUST_NOT_BE_NULL, "innerEvaluator"));

        this.innerEvaluator = innerEvaluator;
    }

    @Override
    public boolean evaluate(Collection<String> stringCollection) {
        requireNonNull(stringCollection, () -> format(PARAM_MUST_NOT_BE_NULL, "stringCollection"));

        return !this.innerEvaluator.evaluate(stringCollection);
    }
}
