package org.example.property;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class YamlParserTest {
    private static final String YAML_PATH = "src/test/resources/test.yml";


    @Test
    public void yamlParseTest() {
        YamlParser yaml = YamlParser.getInstance();
        yaml.load(YAML_PATH);



        Assertions.assertThat(yaml.value("google")).isNull();
        Assertions.assertThat(yaml.value("google.test")).isEqualTo(10);
        Assertions.assertThat(yaml.value("google.test2")).isNull();
        Assertions.assertThat(yaml.value("google.test2.test3")).isNotNull();
        Assertions.assertThat(yaml.value("google.test2.test4.test5.test6.t7.t8.t9")).isNotNull();
        Assertions.assertThat(yaml.value("google.test2.test4.test5.test6.t99.t8.t9")).isNull();


    }

}