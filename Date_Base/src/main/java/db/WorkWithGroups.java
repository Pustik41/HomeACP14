package db;

import models.Group;
import models.Student;
import models.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class WorkWithGroups {
    private Connection connection;

    public WorkWithGroups(Connection connection) {
        this.connection = connection;
    }

    public List<Group> getAllGropupsByCondition(String condition){
        List<Group> result = new LinkedList<>();
        String sqlRequest = "SELECT * FROM groups";

        if(condition != null){
            sqlRequest += condition;
        }

        sqlRequest += ";";

        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlRequest)) {

            while (resultSet.next()) {

                Group group = new Group(resultSet.getString("name_group"));
                group.setGroup_id(resultSet.getInt("group_id"));

                result.add(group);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public boolean createGroup(Group group){
        String sqlRequest = "INSERT INTO groups(name_group) values(?);";
        if(group != null){
            String searchCopy = " WHERE name_group = '" + group.getName() + "'";

            if(getAllGropupsByCondition(searchCopy).isEmpty()){

                try(PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest);) {

                    preparedStatement.setString(1, group.getName());

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

    public Group getGroupById(Integer id){
        if(id != null && id > 0){
            String sqlRequest = " WHERE group_id=" + id;

            List<Group> result = getAllGropupsByCondition(sqlRequest);

            return result.isEmpty() ? null : result.get(0);
        }

        return null;
    }

    public boolean updateGroupInfo(Group group){
        if(group != null){

            Group changeGroup = getGroupById(group.getGroup_id());

            if(changeGroup != null){
                String sqlUpdateRequest = "UPDATE groups set name_group=? WHERE group_id=?; ";

                try(PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdateRequest);) {

                    preparedStatement.setString(1, group.getName());
                    preparedStatement.setInt(2, group.getGroup_id());

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

    public List<Student> getStudentsListInThisGroup(Integer id){
        List<Student> result = new ArrayList<>();

        if(getGroupById(id) != null){
            String sqlGetStudentsList =" WHERE s.group_id=" + id;
            result = new WorkWithStudents(connection).getAllStudentsByCondition(sqlGetStudentsList);
        }

        return result.isEmpty() ? null : result ;
    }

    public boolean assingGroupSubjects(List<Subject> subjects, Integer group_id) {
        if(subjects != null && group_id!=null && getGroupById(group_id) != null &&
                new WorkWithSubjects(connection).getAllSubjectsByCondition(null).containsAll(subjects)){

            String sqlRequest = "INSERT INTO group_subjects(subject_id, group_id) values(?,?);";

            try( PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest)) {

                for (Subject subject: subjects) {
                    preparedStatement.setInt(1, subject.getSubject_id());
                    preparedStatement.setInt(2, group_id);
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

    public List<Integer> getGroupSubjects_id(Integer group_id){
        List<Integer> result = new ArrayList<>();

        if(getGroupById(group_id) != null){
            String sqlRequest = "SELECT subject_id FROM group_subjects where group_id=" + group_id + ";";

            try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlRequest)) {

                while (resultSet.next()){
                    result.add(resultSet.getInt("subject_id"));
                }

            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }

        return result.isEmpty() ? null : result;
    }

    public boolean deleteGroup(Group group){

        if(group != null){
            List<Group> result = getAllGropupsByCondition( " WHERE name_group='" + group.getName() + "';");

            if(!result.isEmpty()) {
                String sqlDeletegroup = "DELETE from groups WHERE name_group=?;";
                String sqlDeletegroupSubjects = "DELETE from group_subjects WHERE group_id=?;";

                try  {
                    PreparedStatement preparedStatement = connection.prepareStatement(sqlDeletegroupSubjects);
                    preparedStatement.setInt(1, result.get(0).getGroup_id());
                    preparedStatement.execute();

                    preparedStatement = connection.prepareStatement(sqlDeletegroup);
                    preparedStatement.setString(1, group.getName());
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
