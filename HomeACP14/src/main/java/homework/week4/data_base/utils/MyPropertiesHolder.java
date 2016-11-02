package homework.week4.data_base.utils;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Котято on 20.09.2016.
 */
public class MyPropertiesHolder {

    private static final Properties PROP = init();

    private static Properties init() {
        Properties properties = new Properties();

        try {
            properties.load(MyPropertiesHolder.class
                    .getResourceAsStream("/home/sql/db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }

    public static String get(String key){
        return PROP.getProperty(key);
    }
}
