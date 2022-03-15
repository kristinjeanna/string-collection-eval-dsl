package com.github.kristinjeanna.scedsl;

public final class Defaults {

    private static final Defaults instance = new Defaults();

    public static Defaults instance() {
        return instance;
    }

    public static void reset() {
        instance().ignoreCase(true);
    }

    private boolean ignoreCase = true;

    private Defaults() {}

    public boolean ignoreCase() {
        return this.ignoreCase;
    }

    public void ignoreCase(boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
    }
}
