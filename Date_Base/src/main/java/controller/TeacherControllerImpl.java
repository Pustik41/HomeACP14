package controller;

import db.WorkWithTeachers;
import models.Teacher;

import java.sql.Connection;
import java.util.List;


public class TeacherControllerImpl implements IController <Teacher, Integer> {

    Connection connection;
    WorkWithTeachers wwt;

    public TeacherControllerImpl(Connection connection) {
        this.connection = connection;
        wwt = new WorkWithTeachers(connection);
    }

    @Override
    public List<Teacher> getAll() {
        return wwt.getAllTeachersByCondition(null);
    }

    @Override
    public boolean create(Teacher obj) {
        return wwt.createTeacher(obj);
    }

    @Override
    public boolean update(Teacher obj) {
        return wwt.updateTeacherInfo(obj);
    }

    @Override
    public Teacher getObjectById(Integer id) {
        return wwt.getTeacherById(id);
    }

    @Override
    public boolean delete(Teacher obj) {
        return wwt.deleteTeacher(obj);
    }

    public Teacher getTeacherWithMaxExp(){
        return wwt.getWithMaxEXP();
    }

    public Teacher getTeacherWithMinExp(){
        return wwt.getWithMinEXP();
    }

    public List<Teacher> getTeacherWithMoreExpThan(Integer experience){
        return wwt.getWithEXPMoreThan(experience);
    }

    public List<Teacher> getTeacherWithLessThan(Integer experience){
        return wwt.getWithEXPLessThan(experience);
    }
}
