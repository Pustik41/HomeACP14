package controller;

import db.WorkWithSubjects;
import models.Group;
import models.Subject;

import java.sql.Connection;
import java.util.List;


public class SubjectsControllerImpl implements IController<Subject, Integer> {

    private Connection connection;
    private WorkWithSubjects wwsub;

    public SubjectsControllerImpl(Connection connection) {
        this.connection = connection;
        wwsub = new WorkWithSubjects(connection);
    }

    @Override
    public List<Subject> getAll() {
        return wwsub.getAllSubjectsByCondition(null);
    }

    @Override
    public boolean create(Subject obj) {
        return wwsub.createSubject(obj);
    }

    @Override
    public boolean update(Subject obj) {
        return wwsub.updateSubjectInfo(obj);
    }

    @Override
    public Subject getObjectById(Integer id) {
        return wwsub.getSubjectById(id);
    }

    @Override
    public boolean delete(Subject obj) {
        return wwsub.deleteSubject(obj);
    }

    public List<Group> getListGroupsStudyingSubject(Integer subject_id){
        return wwsub.getListGroupsStudyingSubject(subject_id);
    }

    public List<Subject> getSubjectsListThatStudyAllGroups(){
        return wwsub.getSubjectsListThatStudyAllGroups();
    }

    public List<Subject> getSubjectsByDescription(String description){
        return wwsub.getSubjectsByDescription(description);
    }

    public List<Subject> getAllByCondition(String condition) {
        return wwsub.getAllSubjectsByCondition(condition);
    }

    public Integer getAvgMarkOnSubjectAmongAllStudents(Integer subject_id){
        return  wwsub.getAvgMarkOnSubjectAmongAllStudents(subject_id);
    }

    public  Integer  getAvgMarkOnSubjectByGroup(Integer group_id, Integer subject_id){
        return wwsub.getAvgMarkOnSubjectByGroup(group_id, subject_id);
    }


}
