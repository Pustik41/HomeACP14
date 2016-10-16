package data_base.utils;

import java.io.IOException;
import java.util.Properties;


    public class MyPropertiesHolder  {

        private static final Properties PROP = init();

        private static Properties init() {
            Properties properties = new Properties();

            try {
                properties.load(MyPropertiesHolder.class
                        .getResourceAsStream("/db.properties"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            return properties;
        }

        public static String get(String key){
            return PROP.getProperty(key);
        }
    }


