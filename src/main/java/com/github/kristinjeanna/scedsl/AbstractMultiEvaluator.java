package com.github.kristinjeanna.scedsl;

import static com.github.kristinjeanna.scedsl.Messages.PARAM_MUST_NOT_BE_NULL;
import static java.lang.String.format;
import static java.util.Collections.unmodifiableList;
import static java.util.Objects.requireNonNull;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractMultiEvaluator implements Evaluator {

    protected final List<Evaluator> evaluators;

    public AbstractMultiEvaluator(List<Evaluator> evaluators) {
        requireNonNull(evaluators, () -> format(PARAM_MUST_NOT_BE_NULL, "evaluators"));

        List<Evaluator> list = new LinkedList<>();
        list.addAll(evaluators);
        this.evaluators = unmodifiableList(list);
    }
}
