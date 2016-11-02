package homework.week4.data_base.db;

import homework.week4.data_base.models.Group;
import homework.week4.data_base.models.Student;
import homework.week4.data_base.models.Subject;
import homework.week4.data_base.models.Teacher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Котято on 06.10.2016.
 */
public class InfoAbout {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    private static final String STUDENTS_REQUEST = "SELECT s.firstname, s.lastname, s.mail, g.name_group " +
            "FROM students s INNER JOIN groups g ON s.group_id = g.group_id ORDER BY s.lastname;";
    private static final String GROUPS_REQUEST = "SELECT group_id, name_group FROM groups ORDER BY name_group;";
    private static final String TEACHERS_REQUEST = "SELECT t.firstname, t.lastname, t.mail, t.experience, s.name  " +
            "FROM teachers t INNER JOIN subjects s ON t.subject_id = s.subject_id ORDER BY t.lastname;";
    private static final String SUBJECTS_REQUEST = "SELECT name, description FROM subjects ORDER BY name;";

    private List result;

    public InfoAbout(Connection connection) {

        this.connection = connection;
        result = new LinkedList();

        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Group> getGroupsList(){

        try{
            resultSet = statement.executeQuery(GROUPS_REQUEST);
            while (resultSet.next()){
                String name = resultSet.getString("name_group");
                //int id = resultSet.getInt("group_id");
                result.add(new Group(name));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  result;
    }

    public List<Student> getStudentsList(){
        try{
            statement = connection.createStatement();
            resultSet = statement.executeQuery(STUDENTS_REQUEST);

            while (resultSet.next()){
                String name = resultSet.getString("s.firstname");
                String lastName = resultSet.getString("s.lastname");
                String mail = resultSet.getString("s.mail");
                String groupName = resultSet.getString("g.name_group");
                result.add(new Student(name, lastName, mail, groupName));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<Teacher> getTeacherList(){
        try{
            statement = connection.createStatement();
            resultSet = statement.executeQuery(TEACHERS_REQUEST);

            while (resultSet.next()){
                String name = resultSet.getString("t.firstname");
                String lastName = resultSet.getString("t.lastname");
                String mail = resultSet.getString("t.mail");
                int experience = resultSet.getInt("t.experience");
                String subject = resultSet.getString("s.name");

                result.add(new Teacher(name, lastName, mail, experience, subject));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<Subject> getSubjectsList(){
        try{
            resultSet = statement.executeQuery(SUBJECTS_REQUEST);

            while (resultSet.next()){
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                result.add(new Subject(name, description));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

     void close(){
        try {

            if(!resultSet.isClosed()){
                resultSet.close();
            }

            if(!statement.isClosed()){
            statement.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
