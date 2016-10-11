package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {

    public static Connection getConnection() throws SQLException {

        Connection connection = DriverManager.getConnection(MyPropertiesHolder.get("db.url"),
                MyPropertiesHolder.get("db.user"),
                MyPropertiesHolder.get("db.pass"));

        return connection;
    }
}
