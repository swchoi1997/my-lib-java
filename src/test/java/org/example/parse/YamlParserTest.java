package org.example.parse;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import org.assertj.core.api.Assertions;
import org.example.base.Base;
import org.junit.jupiter.api.Test;

class YamlParserTest {
    private static final String YAML_PATH = "src/main/resources/application.yml";


    @Test
    public void yamlParseTest() {
        YamlParser yaml = YamlParser.getInstance();
        yaml.read(YAML_PATH);



        Assertions.assertThat(yaml.Value("google")).isNull();
        Assertions.assertThat(yaml.Value("google.test")).isEqualTo(10);
        Assertions.assertThat(yaml.Value("google.test2")).isNull();
        Assertions.assertThat(yaml.Value("google.test2.test3")).isNotNull();
        Assertions.assertThat(yaml.Value("google.test2.test4.test5.test6.t7.t8.t9")).isNotNull();
        Assertions.assertThat(yaml.Value("google.test2.test4.test5.test6.t99.t8.t9")).isNull();


    }

}