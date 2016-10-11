package db;

import models.Group;
import models.Subject;
import models.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkWithSubjects {

    private Connection connection;

    public WorkWithSubjects(Connection connection) {
        this.connection = connection;
    }

    public List<Subject> getAllSubjectsByCondition(String sqlCondition){

        List<Subject> result = new ArrayList<>();
        String sqlRequest = "SELECT * FROM subjects";

        if(sqlCondition != null){
            sqlRequest += sqlCondition;
        }

        sqlRequest += ";";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlRequest)){

            while (resultSet.next()){

                String subject_name = resultSet.getString("name");
                String subject_description = resultSet.getString("description");
                int subject_id = resultSet.getInt("subject_id");

                Subject tmp = new Subject(subject_name, subject_description);
                tmp.setSubject_id(subject_id);

                result.add(tmp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public boolean createSubject(Subject subject){

        if(subject != null){
            String sqlSearchCopy = " WHERE name='" + subject.getSubjectName() +
                    "' AND description='" + subject.getDescription() +"'";
            if(getAllSubjectsByCondition(sqlSearchCopy).isEmpty()){
                String sqlINSERT = "INSERT INTO subjects(name, description) values(?,?);";

                try(PreparedStatement preparedStatement = connection.prepareStatement(sqlINSERT);) {

                    preparedStatement.setString(1, subject.getSubjectName());
                    preparedStatement.setString(2, subject.getDescription());

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

    public Subject getSubjectById(Integer id){

        if(id != null && id > 0){
            String sqlRequest = " WHERE subject_id=" + id;
            List<Subject> result = getAllSubjectsByCondition(sqlRequest);

            return result.isEmpty() ? null : result.get(0);
        }

        return null;
    }

    public boolean updateSubjectInfo(Subject subject){

        if(subject != null && getSubjectById(subject.getSubject_id()) != null){
            String sqlSET = "UPDATE subjects SET name=?, description=? WHERE subject_id=?;";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSET);){

                preparedStatement.setString(1, subject.getSubjectName());
                preparedStatement.setString(2, subject.getDescription());
                preparedStatement.setInt(3, subject.getSubject_id());

                preparedStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }

            return true;
        }
        return false;
    }

    public List<Group> getListGroupsStudyingSubject(Integer subject_id) {
        List<Group> result  = new ArrayList<>();

        if(subject_id != null && subject_id > 0){
            String sqlRequest = "SELECT * FROM groups WHERE group_id IN (SELECT group_id from group_subjects WHERE subject_id =" + subject_id + ");";

            try(Statement statment = connection.createStatement();
                ResultSet resultSet = statment.executeQuery(sqlRequest)) {

                while (resultSet.next()){
                    Group tmp = new Group(resultSet.getString("name_group"));
                    tmp.setGroup_id(resultSet.getInt("group_id"));

                    result.add(tmp);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return result;
    }

    public  List<Subject> getSubjectsListThatStudyAllGroups(){
        List<Subject> result = new ArrayList<>();
        List<Subject> allSubject = getAllSubjectsByCondition(null);
        List<Group> allGroup = new WorkWithGroups(connection).getAllGropupsByCondition(null);

        for (Subject subject: allSubject) {
            if(getListGroupsStudyingSubject(subject.getSubject_id()).containsAll(allGroup)){
                result.add(subject);
            }
        }

        return result;
    }


    public List<Subject> getSubjectsByDescription(String description) {
        List<Subject> result = null;

        if(description.equals("Technical") || description.equals("Humanities")){
            String sqlReguest = " WHERE description='" + description + "'";
            result = getAllSubjectsByCondition(sqlReguest);
        }

        return result;
    }

    public Integer getAvgMarkOnSubjectAmongAllStudents(Integer subject_id) {
        int avgMark = 0;
        if(getSubjectById(subject_id)!= null){
            String sqlRequest = "SELECT avg(sp.progress) FROM students_progress sp " +
                    " WHERE sp.subject_id=" + subject_id + " Group by sp.subject_id;";

            try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlRequest);) {

                while (resultSet.next()){
                    avgMark = resultSet.getInt(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            }
        }

        return avgMark;
    }

    public Integer getAvgMarkOnSubjectByGroup(Integer group_id, Integer subject_id) {
        int avgMark = 0;

        if( getSubjectById(subject_id) != null &&
                new WorkWithGroups(connection).getGroupById(group_id) != null){

            String sqlRequest = "SELECT avg(sp.progress) FROM students_progress sp  " +
                    "WHERE sp.student_id IN(SELECT student_id FROM students WHERE group_id =" + group_id + ") " +
                    "AND sp.subject_id =" + subject_id + ";";

            try( Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlRequest)){

                while (resultSet.next()) {
                    avgMark = resultSet.getInt(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }

        return avgMark;
    }

    public boolean deleteSubject(Subject subject) {
        if(subject != null){
            List<Subject> result = getAllSubjectsByCondition( " WHERE name='" + subject.getSubjectName() + "'");
            if(!result.isEmpty()) {
                String sqlDeleteSubject = "DELETE from subjects WHERE subject_id=?;";
                WorkWithTeachers wwt = new WorkWithTeachers(connection);
                List<Teacher> teacher = wwt.getAllTeachersByCondition(" WHERE t.subject_id=" + result.get(0).getSubject_id());

                if(!teacher.isEmpty()){
                    wwt.deleteTeacher(teacher.get(0));
                }

                try (PreparedStatement preparedStatement = connection.prepareStatement(sqlDeleteSubject);) {

                    preparedStatement.setInt(1, result.get(0).getSubject_id());
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
