package com.github.kristinjeanna.scedsl;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.nullToEmpty;
import static com.github.kristinjeanna.scedsl.Messages.PARAM_MUST_NOT_BE_NULL;
import static com.github.kristinjeanna.scedsl.Messages.PARAM_MUST_NOT_BE_NULL_OR_EMPTY;
import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.Optional;

public final class BasicEvaluator implements Evaluator {

    private final String soughtString;

    public BasicEvaluator(String soughtString) {
        final String ss = nullToEmpty(soughtString).trim();
        checkArgument(!ss.isEmpty(), PARAM_MUST_NOT_BE_NULL_OR_EMPTY, "soughtString");

        this.soughtString = ss;
    }

    @Override
    public boolean evaluate(Collection<String> stringCollection) {
        requireNonNull(stringCollection, () -> format(PARAM_MUST_NOT_BE_NULL, "stringCollection"));

        final boolean ignoreCase = Defaults.instance().ignoreCase();

        Optional<String> first = ignoreCase //
                ? stringCollection.stream() //
                        .filter(s -> s.equalsIgnoreCase(this.soughtString)) //
                        .findFirst() //
                : stringCollection.stream() //
                        .filter(s -> s.equals(this.soughtString)) //
                        .findFirst();

        return first.isPresent();
    }
}
