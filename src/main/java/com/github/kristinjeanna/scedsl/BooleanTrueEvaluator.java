package com.github.kristinjeanna.scedsl;

import java.util.Collection;

public final class BooleanTrueEvaluator implements Evaluator {

    @Override
    public boolean evaluate(Collection<String> stringCollection) {
        return true;
    }
}
