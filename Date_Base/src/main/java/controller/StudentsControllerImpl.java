package controller;

import db.WorkWithStudents;
import models.Student;

import java.sql.Connection;
import java.util.List;
import java.util.Map;


public class StudentsControllerImpl implements IController <Student, Integer>  {

    private Connection connection;
    private WorkWithStudents wws;

    public StudentsControllerImpl(Connection connection) {
        this.connection = connection;
        wws = new WorkWithStudents(connection);
    }

    @Override
    public List<Student> getAll() {
        return wws.getAllStudentsByCondition(null);
    }

    @Override
    public boolean create(Student obj) {
        return wws.createStudent(obj);
    }

    @Override
    public boolean update(Student obj) {
        return wws.updateStudentInfo(obj);
    }

    @Override
    public Student getObjectById(Integer id) {
        return wws.getStudentById(id);
    }

    @Override
    public boolean delete(Student obj) {
        return wws.deleteStudent(obj);
    }

    public boolean rateStudentBySubject(Map<Integer, Integer> marks, Integer student_id){
        return wws.rateStudentBySubject(marks, student_id);
    }
}
