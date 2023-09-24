package org.example.shell;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class RunShellTest {

    @Test
    public void runShellTest() {
        RunShell whoami = new RunShell("whoami");

        String run = whoami.run();

        Assertions.assertThat(run).isEqualTo("forest_choi");
    }

}