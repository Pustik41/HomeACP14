package data_base.dao;

import data_base.model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;


public class SubjectDao implements IDao<Subject, Integer> {

    public static final String TECHNICAL = "Technical";
    public static final String HUMANITIES = "Humanities";
    private EntityManagerFactory factory;

    public SubjectDao(EntityManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<Subject> getAll() {
        EntityManager manager = factory.createEntityManager();

        try {
            TypedQuery<Subject> query = manager.createQuery("SELECT s from Subject s", Subject.class);
            return query.getResultList();
        } finally {
            manager.close();
        }
    }

    @Override
    public boolean create(Subject subject) {
        if(subject != null){
            EntityManager manager = factory.createEntityManager();
            EntityTransaction transaction = manager.getTransaction();

            try {
                transaction.begin();
                manager.persist(subject);
                transaction.commit();
                return true;
            } catch (Exception e){
                transaction.rollback();
                return false;
            } finally {
                manager.close();
            }
        }
        return false;
    }

    @Override
    public boolean update(Subject subject) {
        if(subject != null) {
            EntityManager manager = factory.createEntityManager();
            EntityTransaction transaction = manager.getTransaction();

            Subject originSubject = manager.find(Subject.class, subject.getId());

            try {
                transaction.begin();

                originSubject.setDescription(subject.getDescription());
                originSubject.setSubjectName(subject.getSubjectName());

                manager.merge(originSubject);
                transaction.commit();
                return true;
            } catch (Exception e){
                transaction.rollback();
                return false;
            } finally {
                manager.close();
            }
        }
        return false;
    }

    @Override
    public Subject getObjectById(Integer id) {
        if(id != null && id > 0){
            EntityManager manager = factory.createEntityManager();

            try {
                return manager.find(Subject.class, id);
            } finally {
                manager.close();
            }
        }

        return null;
    }

    @Override
    public boolean delete(Subject subject) {
        if(subject != null){

            EntityManager manager = factory.createEntityManager();
            EntityTransaction transaction = manager.getTransaction();

            subject = manager.find(Subject.class, subject.getId());

            try {
                transaction.begin();
                manager.remove(subject);
                transaction.commit();
                return true;
            } catch (Exception e){
                transaction.rollback();
                return false;
            } finally {
                manager.close();
            }
        }

        return false;
    }

    public List<Group> getListGroupsStudyingSubject(Integer id) {
        if(id != null && id > 0){

            EntityManager manager = factory.createEntityManager();

            Subject subject = manager.find(Subject.class, id);
            List<GroupSubjects> groupSubjectses = subject.getGroupSubjectses();
            List<Group> groups = new ArrayList<>();

            groupSubjectses.stream().forEach(groupSubjects -> {groups.add(groupSubjects.getGroup());});

            return groups;
        }

        return null;
    }

    public  List<Subject> getSubjectsListThatStudyAllGroups(){
        List<Subject> result = new ArrayList<>();
        List<Subject> subjects = getAll();
        List<Group> allGroup = new GroupDao(factory).getAll();

        subjects.stream().forEach(subject -> {
            if(getListGroupsStudyingSubject(subject.getId()).containsAll(allGroup)){
                result.add(subject);}
        });

        return result;
    }


    public List<Subject> getHumanitiesSubjectsList() {
        List<Subject> result = new ArrayList<>();
        List<Subject> subjects = getAll();

        subjects.stream().forEach(subject -> {
            if(subject.getDescription().equals(HUMANITIES)){
                result.add(subject);
        }});

        return result;
    }

    public List<Subject> getTechnicalsSubjectsList() {
        List<Subject> result = new ArrayList<>();
        List<Subject> subjects = getAll();

        subjects.stream().forEach(subject -> {
            if(subject.getDescription().equals(TECHNICAL)){
                result.add(subject);
            }});

        return result;
    }

    public double getAvgMarkOnSubjectByAllStudents(Integer subjectId) {


        if(subjectId != null && subjectId > 0){
            EntityManager manager = factory.createEntityManager();

            String sqlRequest = "SELECT avg(sp.mark) FROM StudentProgress sp  where subject.id=:subjectId";

            TypedQuery<Double> query = manager.createQuery(sqlRequest, Double.class);

            query.setParameter("subjectId", subjectId);

            return  query.getSingleResult();
        }

        return 0;
    }

   public double getAvgMarkOnSubjectByGroup(Group group, Subject subject) {

        if( group != null && subject != null){

            EntityManager manager = factory.createEntityManager();

            String sqlRequest = "SELECT avg(sp.mark) FROM StudentProgress sp " +
                    " WHERE sp.student.id IN(SELECT s.id FROM Student s WHERE group.id=:groupId)" +
                    " AND sp.subject.id=:subjectId";

            TypedQuery<Double> query = manager.createQuery(sqlRequest, Double.class);

            query.setParameter("groupId", group.getId());
            query.setParameter("subjectId", subject.getId());

            return  query.getSingleResult();

        }

        return 0;
    }

}
