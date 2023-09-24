package org.example.parse;

import static org.example.base.ExceptionMessage.ILLEGAL_ARGUMENT_EXCEPTION;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collections;
import java.util.Map;
import org.apache.log4j.Logger;
import org.example.base.Base;
import org.yaml.snakeyaml.Yaml;

public class YamlParser {

    private static final Logger LOG = Logger.getLogger(YamlParser.class);

    private Map<String, Object> yamlArguments;

    private YamlParser() {}

    public static YamlParser getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final YamlParser INSTANCE = new YamlParser();
    }

    public void read(final String yamlPath){
        try {
            this.yamlArguments = new Yaml()
                    .load(new FileReader(yamlPath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



    public Object Value(final String keys) {
        if (this.checkEmpty()) return null;
        if (keys.isEmpty() || keys.isBlank()) {
            LOG.error(ILLEGAL_ARGUMENT_EXCEPTION.getMessage());
            throw new IllegalArgumentException();
        }

        String[] splitedKeys = keys.split(Base.DOT.getValue());


        Object value = this.yamlArguments.get(splitedKeys[0]);

        if (splitedKeys.length == Integer.parseInt(Base.ONE.getValue())) {
            return this.checkMap(value);
        }


        for (int i = 1; i < splitedKeys.length; i++) {
            if (!this.isMap(value)) return null;
            Map<String, Object> tmpValueMap = Collections.unmodifiableMap((Map<String, Object>) value);
            if (tmpValueMap.containsKey(splitedKeys[i])) {
                Object tmpValue = tmpValueMap.get(splitedKeys[i]);
                if (this.isMap(tmpValue)) {
                    if (splitedKeys.length - 1 == i) {
                        return null;
                    }
                    value = tmpValue;
                    continue;
                }
                return tmpValue;
            } else {
                return null;
            }
        }
        return null;
    }

    private Object checkMap(final Object value) {
        if (value instanceof Map) {
            return null;
        }
        return value;
    }

    private boolean isMap(final Object value) {
        return value instanceof Map<?, ?>;
    }

    private boolean checkEmpty() {
        return this.yamlArguments.isEmpty();
    }
}
