package data_base.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class ConnectionFactory {

    private static final String PROPERTIES_PATH = "db.properties";
    //private static Properties properties = new Properties(); ;

    public static Connection getConnection() throws SQLException, IOException {

        //properties.load(ConnectionFactory.class.getResourceAsStream(PROPERTIES_PATH));

        Connection connection = DriverManager.getConnection(MyPropertiesHolder.get("url"),
                MyPropertiesHolder.get("user"),
                MyPropertiesHolder.get("pass"));

        return connection;
    }
}
