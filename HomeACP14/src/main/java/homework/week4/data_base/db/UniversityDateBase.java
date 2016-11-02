package homework.week4.data_base.db;

import homework.week4.data_base.utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * Created by Котято on 06.10.2016.
 */
public class UniversityDateBase {

    private Connection connection;

    public UniversityDateBase() {
        try {
            connection = ConnectionFactory.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  InfoAbout getInfoAbout(){
        return new InfoAbout(connection);
    }



    public boolean closeConnection(){

        try {
            if(!connection.isClosed()){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

}
