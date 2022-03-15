package com.github.kristinjeanna.scedsl;

import static com.github.kristinjeanna.scedsl.Messages.PARAM_MUST_NOT_BE_NULL;
import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.List;

public final class XorEvaluator extends AbstractMultiEvaluator implements Evaluator {

    public XorEvaluator(List<Evaluator> evaluators) {
        super(evaluators);
    }

    @Override
    public boolean evaluate(Collection<String> stringCollection) {
        requireNonNull(stringCollection, () -> format(PARAM_MUST_NOT_BE_NULL, "stringCollection"));

        long count = this.evaluators.stream() //
                .filter(e -> e.evaluate(stringCollection)) //
                .count();

        return count == 1;
    }
}
