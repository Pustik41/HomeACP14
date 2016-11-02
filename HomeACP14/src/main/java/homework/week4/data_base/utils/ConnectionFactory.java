package homework.week4.data_base.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Котято on 05.10.2016.
 */
public class ConnectionFactory {

    public static Connection getConnection() throws SQLException {

        Connection connection = DriverManager.getConnection(MyPropertiesHolder.get("db.url"),
                MyPropertiesHolder.get("db.user"),
                MyPropertiesHolder.get("db.pass"));

        return connection;
    }
}
