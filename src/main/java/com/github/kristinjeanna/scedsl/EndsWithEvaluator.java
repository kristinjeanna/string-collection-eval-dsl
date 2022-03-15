package com.github.kristinjeanna.scedsl;

import static com.github.kristinjeanna.scedsl.Messages.PARAM_MUST_NOT_BE_NULL;
import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.Optional;

public final class EndsWithEvaluator implements Evaluator {

    private final String stringFragment;

    public EndsWithEvaluator(String stringFragment) {
        requireNonNull(stringFragment, () -> format(PARAM_MUST_NOT_BE_NULL, "stringFragment"));

        this.stringFragment = stringFragment;
    }

    @Override
    public boolean evaluate(Collection<String> stringCollection) {
        requireNonNull(stringCollection, () -> format(PARAM_MUST_NOT_BE_NULL, "stringCollection"));

        final boolean ignoreCase = Defaults.instance().ignoreCase();

        Optional<String> result = null;
        if (ignoreCase) {
            result = stringCollection.stream() //
                    .filter(s -> s.toLowerCase().endsWith(this.stringFragment.toLowerCase())) //
                    .findFirst();
        }
        else {
            result = stringCollection.stream() //
                    .filter(s -> s.endsWith(this.stringFragment)) //
                    .findFirst();
        }

        return result.isPresent();
    }
}
