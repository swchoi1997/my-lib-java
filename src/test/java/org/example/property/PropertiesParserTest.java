package org.example.property;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class PropertiesParserTest {

    private static final String PROPERTIES_PATH = "src/test/resources/test.properties";

    @Test
    public void propertiesParser() {
        PropertiesParser parser = PropertiesParser.getInstance();
        parser.load(PROPERTIES_PATH);

        Assertions.assertThat(parser.value("t1.t2.t3")).isEqualTo("ttt1");
        Assertions.assertThat(parser.value("t1.t4.t5")).isEqualTo("ttt2");
        Assertions.assertThat(parser.value("t1.t2.t4.t5")).isEqualTo("qwerty");



    }

}