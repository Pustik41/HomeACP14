package db;

import models.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class WorkWithTeachers {
    Connection connection;

    private static final String TEACHERS_REQUEST = "SELECT t.*, s.name from teachers t INNER JOIN subjects s ON t.subject_id = s.subject_id";

    public WorkWithTeachers(Connection connection) {
        this.connection = connection;
    }

    public List<Teacher> getAllTeachersByCondition (String sqlCondition){
        List<Teacher> result = new LinkedList<>();

        String salRequest = TEACHERS_REQUEST;
        if(sqlCondition != null){
            salRequest += sqlCondition;
        }

        salRequest +=";";

        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(salRequest)) {

            while (resultSet.next()){
                String name = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                int experience =resultSet.getInt("experience");
                int subject_id = resultSet.getInt("subject_id");
                int teacher_id = resultSet.getInt("teacher_id");
                String subject_name = resultSet.getString("name");

                Teacher teacher = new Teacher(name, lastName, subject_id, experience);
                teacher.setSubject_name(subject_name);
                teacher.setId(teacher_id);

                result.add(teacher);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public boolean createTeacher(Teacher obj){
        String sqlRequest = "INSERT INTO teachers(firstname, lastname, experience, subject_id) values(?,?,?,?);";
        if(obj != null){
            String findCopy = " WHERE t.firstname='" + obj.getFirstName() + "' AND t.lastname='" + obj.getLastName() +
                    "'AND t.experience=" + obj.getExperience() + " AND t.subject_id=" + obj.getSubject_id();

            if(getAllTeachersByCondition(findCopy).isEmpty()) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest);) {

                    preparedStatement.setString(1, obj.getFirstName());
                    preparedStatement.setString(2, obj.getLastName());
                    preparedStatement.setInt(3, obj.getExperience());
                    preparedStatement.setInt(4, obj.getSubject_id());

                    preparedStatement.execute();

                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
                return true;
            }
        }

        return false;
    }

    public Teacher getTeacherById(Integer id){
        List<Teacher> result = new ArrayList<>();
        if (id != null && id > 0){
            String sqlCondition = " WHERE t.teacher_id=" + id;

            result = getAllTeachersByCondition(sqlCondition);
        }

        return  result.isEmpty() ? null : result.get(0);
    }

    public boolean updateTeacherInfo(Teacher obj){
        if(obj != null && getTeacherById(obj.getId()) != null){

            String sqlRequest = "UPDATE teachers SET firstname= ?, lastname = ?, experience= ?, subject_id=? WHERE teacher_id= ?;";

            try(PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest);) {
                preparedStatement.setString(1, obj.getFirstName());
                preparedStatement.setString(2, obj.getLastName());
                preparedStatement.setInt(3, obj.getExperience());
                preparedStatement.setInt(4, obj.getSubject_id());
                preparedStatement.setInt(5, obj.getId());

                preparedStatement.execute();

            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }

            return true;
        }

        return false;
    }

    public Teacher getWithMaxEXP(){

        String sqlMaxExpRequest = " WHERE t.experience = (SELECT MAX(experience) FROM teachers)" ;

        return getAllTeachersByCondition(sqlMaxExpRequest).get(0);
    }

    public Teacher getWithMinEXP(){

        String sqlMaxExpRequest = " WHERE t.experience = (SELECT MIN(experience) FROM teachers)" ;

        return getAllTeachersByCondition(sqlMaxExpRequest).get(0);
    }

    public List<Teacher> getWithEXPMoreThan(Integer exp){
        List<Teacher> result = null;

        if(exp != null) {
            String sqlWithExpMoreThan = " WHERE t.experience > " + exp;
            result = getAllTeachersByCondition(sqlWithExpMoreThan);
        }

        return result;
    }

    public List<Teacher> getWithEXPLessThan(Integer exp){
        List<Teacher> result = null;

        if(exp != null) {
            String sqlWithExpMoreThan = " WHERE t.experience < " + exp;
            result = getAllTeachersByCondition(sqlWithExpMoreThan);
        }

        return result;
    }

    public boolean deleteTeacher(Teacher teacher) {
        if(teacher != null){
            List<Teacher> result = getAllTeachersByCondition( " WHERE t.firstname='" + teacher.getFirstName() + "' AND" +
                    " t.lastname='" + teacher.getLastName() + "' AND t.subject_id=" + teacher.getSubject_id());

            if(!result.isEmpty()) {
                String sqlDeleteTeacher = "DELETE from teachers WHERE teacher_id=?;";

                try(PreparedStatement preparedStatement = connection.prepareStatement(sqlDeleteTeacher);)  {

                    preparedStatement.setInt(1, result.get(0).getId());
                    preparedStatement.execute();

                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }

                return true;
            }
        }

        return false;
    }
}
