package org.example.property;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;

public class PropertiesParser implements IProperty{

    private static final Logger LOG = Logger.getLogger(PropertiesParser.class);

    private Properties prop = null;

    private PropertiesParser(){}

    public static PropertiesParser getInstance() { return LazyHolder.INSTANCE; }

    private static class LazyHolder {
        private static final PropertiesParser INSTANCE = new PropertiesParser();
    }

    public void load(final String path) {
        try {
            if (this.getProp() == null) {
                this.prop = new Properties();
            }
            this.prop.load(new FileInputStream(path));


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Object value(final String keys) {
        if (this.getProp() == null) {
            throw new NullPointerException();
        }

        final String value = this.prop.getProperty(keys);
        if (value == null) {
            return "";
        }
        return value.trim();
    }

    private Properties getProp() {
        return prop;
    }
}
