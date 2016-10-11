package db;

import models.Student;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class WorkWithStudents {

    private Connection connection;

    private static final String STUDENTS_REQUEST = "SELECT s.*, g.name_group " +
            "FROM students s INNER JOIN groups g ON s.group_id = g.group_id";

    public WorkWithStudents(Connection connection) {

        this.connection = connection;
    }

    public List<Student> getAllStudentsByCondition (String sqlCondition){
        List<Student> result = new LinkedList<>();

        String salRequest = STUDENTS_REQUEST;
        if(sqlCondition != null){
            salRequest +=  sqlCondition;
        }

        salRequest +=";";

        try(Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(salRequest)) {

            while (resultSet.next()){
                String name = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                int group_id =resultSet.getInt("group_id");
                int student_id = resultSet.getInt("student_id");
                String group_name = resultSet.getString("name_group");

                Student student = new Student(name, lastName, group_id);
                student.setId(student_id);
                student.setGroup_name(group_name);

                result.add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public boolean createStudent(Student obj){
        String sqlRequest = "INSERT INTO students(firstname, lastname, group_id) values(?,?,?);";
        if(obj != null){
            String findCopy = " WHERE s.firstname='" + obj.getFirstName() + "' AND s.lastname='" +
                    obj.getLastName() + "'AND s.group_id=" + obj.getGroup_id();

            if(getAllStudentsByCondition(findCopy).isEmpty()) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest);) {

                    preparedStatement.setString(1, obj.getFirstName());
                    preparedStatement.setString(2, obj.getLastName());
                    preparedStatement.setInt(3, obj.getGroup_id());

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

    public Student getStudentById(Integer id){
        if (id != null && id > 0){
            String sqlCondition = " WHERE s.student_id=" + id;

            List<Student> result = getAllStudentsByCondition(sqlCondition);

            return result.isEmpty() ? null : result.get(0);
        }

        return  null;
    }

    public boolean updateStudentInfo(Student obj){
        if(obj != null && getStudentById(obj.getId()) != null){

            String sqlRequest = "UPDATE students SET firstname= ?, lastname = ?, group_id= ? WHERE student_id= ?;";

            try(PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest);) {
                preparedStatement.setString(1, obj.getFirstName());
                preparedStatement.setString(2, obj.getLastName());
                preparedStatement.setInt(3, obj.getGroup_id());
                preparedStatement.setInt(4, obj.getId());

                preparedStatement.execute();

            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }

            return true;
        }

        return false;
    }


    public boolean rateStudentBySubject(Map<Integer, Integer> markBySubject, Integer student_id) {

        if(markBySubject != null && getStudentById(student_id) != null){
            WorkWithSubjects wws = new WorkWithSubjects(connection);

            for (Map.Entry<Integer, Integer> mark: markBySubject.entrySet()){
                if(wws.getSubjectById(mark.getKey()) == null){
                    return false;
                }
            }

            String sqlRequest = "INSERT INTO students_progress(student_id, subject_id, progress) values(?,?,?);";

            try(PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest)) {

                for (Map.Entry<Integer, Integer> mark: markBySubject.entrySet()){
                    preparedStatement.setInt(1, student_id);
                    preparedStatement.setInt(2, mark.getKey());
                    preparedStatement.setInt(3, mark.getValue());
                    preparedStatement.execute();
                }

            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }

            return true;
        }

        return false;
    }

    public boolean deleteStudent(Student student) {
        if(student != null){
            List<Student> result = getAllStudentsByCondition( " WHERE s.firstname='" + student.getFirstName() + "' AND" +
                    " s.lastname='" + student.getLastName() + "' AND s.group_id=" + student.getGroup_id());

            if(!result.isEmpty()) {
                String sqlDeleteStudent = "DELETE from students WHERE student_id=?;";
                String sqlDeleteStudentProgress = "DELETE from students_progress WHERE student_id=?;";

                try  {
                    PreparedStatement preparedStatement = connection.prepareStatement(sqlDeleteStudentProgress);
                    preparedStatement.setInt(1, result.get(0).getId());
                    preparedStatement.execute();

                    preparedStatement = connection.prepareStatement(sqlDeleteStudent);
                    preparedStatement.setInt(1, result.get(0).getId());
                    preparedStatement.execute();

                    preparedStatement.close();
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
