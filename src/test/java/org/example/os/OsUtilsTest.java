package org.example.os;

import static org.assertj.core.api.Assertions.*;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OsUtilsTest {

    @Test
    public void OsTest() {
        String currentOs = OsUtils.currentOs();
        assertThat(currentOs.toLowerCase().startsWith("mac")).isTrue();
    }

}