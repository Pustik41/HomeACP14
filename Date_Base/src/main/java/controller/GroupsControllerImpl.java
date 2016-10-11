package controller;

import db.WorkWithGroups;
import models.Group;
import models.Student;
import models.Subject;

import java.sql.Connection;
import java.util.List;


public class GroupsControllerImpl implements IController<Group, Integer> {

    Connection connection;
    WorkWithGroups wwg;

    public GroupsControllerImpl(Connection connection) {
        this.connection = connection;
        wwg = new WorkWithGroups(connection);
    }

    @Override
    public List<Group> getAll() {
        return wwg.getAllGropupsByCondition(null);
    }

    @Override
    public boolean create(Group obj) {
        return wwg.createGroup(obj);
    }

    @Override
    public boolean update(Group obj) {
        return wwg.updateGroupInfo(obj);
    }

    @Override
    public Group getObjectById(Integer id) {
        return wwg.getGroupById(id);
    }

    @Override
    public boolean delete(Group obj) {
        return wwg.deleteGroup(obj);
    }

    public List<Student> getStudentsListInThisGroup(Integer id){ return wwg.getStudentsListInThisGroup(id);}

    public boolean assignGroupSubjects(List<Subject> subjects, Integer group_id){
        return wwg.assingGroupSubjects(subjects, group_id);
    }

}
