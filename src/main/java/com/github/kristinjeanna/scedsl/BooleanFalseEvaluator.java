package com.github.kristinjeanna.scedsl;

import java.util.Collection;

public final class BooleanFalseEvaluator implements Evaluator {

    @Override
    public boolean evaluate(Collection<String> stringCollection) {
        return false;
    }
}
