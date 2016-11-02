package data_base.service;

import data_base.dao.GroupDao;
import data_base.dao.SubjectDao;
import data_base.dao.TeacherDao;
import data_base.model.Group;
import data_base.model.Student;
import data_base.model.Subject;
import data_base.model.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

// view -> Service, Manager, Controller(business logic)->DAO
public class DirectorService {

    private GroupDao groupDao;
    private SubjectDao subjectDao;
    private TeacherDao teacherDao;
    private EntityManagerFactory factory;

    public DirectorService() {

        factory = Persistence.createEntityManagerFactory("hibernate-unit");
        groupDao = new GroupDao(factory);
        subjectDao = new SubjectDao(factory);
        teacherDao = new TeacherDao(factory);
    }

   public List<Group> getAllGroups(){
       return groupDao.getAll();
   }

   public boolean createGroup(Group group){
       return groupDao.create(group);
   }

   public boolean deleteGroup(Group group){
       return groupDao.delete(group);
   }

   public Group getGroupByID(Integer group_id){
       return groupDao.getObjectById(group_id);
   }

   public boolean updateGroupInfo(Group group){
       return groupDao.update(group);
   }

   public List<Student> getStunedtsListByGroupId(Integer id){
       return groupDao.getStudentsListInThisGroup(id);
   }

    public boolean assingGroupSubjects(List<Subject> subjects, Group group){
        return groupDao.assingGroupSubjects(subjects, group);
    }

    public List<Subject> getAllSubjects(){
        return subjectDao.getAll();
    }

    public boolean createSubject(Subject subject){
        return subjectDao.create(subject);
    }

    public boolean updateSubjectInfo(Subject subject){
        return subjectDao.update(subject);
    }

    public Subject getSubjectById(Integer id){
        return subjectDao.getObjectById(id);
    }

    public boolean deleteSubject(Subject subject){
        return subjectDao.delete(subject);
    }

    public double getAvgMarkOnSubjectByGroup(Group group, Subject subject){
        return subjectDao.getAvgMarkOnSubjectByGroup(group, subject);
    }

    public  double getAvgMarkOnSubjectByAllStudents(Integer subjectID){
        return subjectDao.getAvgMarkOnSubjectByAllStudents(subjectID);
    }

    public List<Group> getListGroupsStudyingSubject(Integer subjectId){
        return subjectDao.getListGroupsStudyingSubject(subjectId);
    }

    public List<Subject> getSubjectsListThatStudyAllGroups(){
        return subjectDao.getSubjectsListThatStudyAllGroups();
    }

    public List<Subject> getHumanitiesSubjectsList(){
        return subjectDao.getHumanitiesSubjectsList();
    }

    public List<Subject> getTechnicalsSubjectsList(){
        return subjectDao.getTechnicalsSubjectsList();
    }

    public List<Teacher> getAllTeachers(){
        return teacherDao.getAll();
    }

    public boolean createTeacher(Teacher teacher){
        return teacherDao.create(teacher);
    }

    public boolean updateTeacherInfo(Teacher teacher){
        return teacherDao.update(teacher);
    }

    public Teacher getTeacherById(Integer id){
        return teacherDao.getObjectById(id);
    }

    public boolean deleteTeacher(Teacher teacher){
        return teacherDao.delete(teacher);
    }

    public Teacher getTeacherWithMaxExp(){
        return teacherDao.getWithMaxEXP();
    }

    public Teacher getTeacherWithMinExp(){
        return teacherDao.getWithMinEXP();
    }

    public List<Teacher> getTeacherWithExpMoreThan(Integer exp){
        return teacherDao.getWithEXPMoreThan(exp);
    }

    public List<Teacher> getTeacherWithExpLessThan(Integer exp){
        return teacherDao.getWithEXPLessThan(exp);
    }

    public void closeConnection(){
       factory.close();
   }
}
