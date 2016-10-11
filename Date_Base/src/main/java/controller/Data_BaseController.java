package controller;

import utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class Data_BaseController {

    private Connection connection;
    private StudentsControllerImpl sci;
    private TeacherControllerImpl tci;
    private GroupsControllerImpl gci;
    private SubjectsControllerImpl subci;

    public Data_BaseController() {
        try {
            connection = ConnectionFactory.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sci = new StudentsControllerImpl(connection);
        tci =new TeacherControllerImpl(connection);
        gci = new GroupsControllerImpl(connection);
        subci = new SubjectsControllerImpl(connection);
    }

    public StudentsControllerImpl workWithStudents(){
        return sci;
    }

    public TeacherControllerImpl workWithTeachers(){
        return tci ;
    }

    public GroupsControllerImpl workWithGroups(){
        return gci;
    }

    public SubjectsControllerImpl workWithSubjects(){
        return subci;
    }

    public boolean closeConnection(){
        try {
            if(!connection.isClosed()){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
