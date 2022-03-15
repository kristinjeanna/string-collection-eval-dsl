package com.github.kristinjeanna.scedsl;

import org.junit.jupiter.api.BeforeEach;

public abstract class AbstractTestBase {

    @BeforeEach
    public void beforeEach() {
        Defaults.reset();
    }
}
