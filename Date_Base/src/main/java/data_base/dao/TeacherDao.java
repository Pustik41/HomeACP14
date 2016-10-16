package data_base.dao;

import data_base.model.Teacher;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class TeacherDao implements IDao<Teacher, Integer> {

    private EntityManagerFactory factory;

    public TeacherDao(EntityManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<Teacher> getAll() {
        EntityManager manager = factory.createEntityManager();

        TypedQuery<Teacher> query = manager.createQuery("SELECT t FROM Teacher t", Teacher.class);
        return query.getResultList() ;
    }

    @Override
    public boolean create(Teacher teacher) {

        List<Teacher> teachers = getAll();

        if(teacher != null && !teachers.contains(teacher)){
            EntityManager manager = factory.createEntityManager();
            EntityTransaction transaction = manager.getTransaction();

            try {
                transaction.begin();
                manager.persist(teacher);
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
    public boolean update(Teacher teacher) {

        if(teacher != null){

            EntityManager manager = factory.createEntityManager();
            EntityTransaction transaction = manager.getTransaction();

            Teacher originalTeacher = manager.find(Teacher.class, teacher.getId());

            try {
                transaction.begin();

                originalTeacher.setFirstName(teacher.getFirstName());
                originalTeacher.setLastName(teacher.getLastName());
                originalTeacher.setSubject(teacher.getSubject());
                originalTeacher.setExperience(teacher.getExperience());

                manager.merge(teacher);
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
    public Teacher getObjectById(Integer id) {

        if(id != null && id > 0){
            EntityManager manager = factory.createEntityManager();

            return manager.find(Teacher.class, id);
        }

        return null;
    }

    @Override
    public boolean delete(Teacher teacher) {
        if(teacher != null){
            EntityManager manager = factory.createEntityManager();
            EntityTransaction transaction = manager.getTransaction();

            teacher = manager.find(Teacher.class, teacher.getId());

            try {
                transaction.begin();
                manager.remove(teacher);
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

    public Teacher getWithMaxEXP(){

        EntityManager manager = factory.createEntityManager();

        String sqlRequest = "SELECT t FROM Teacher t WHERE t.experience = (SELECT MAX(t.experience) FROM Teacher t)" ;
        TypedQuery<Teacher> query = manager.createQuery(sqlRequest, Teacher.class);

        return query.getSingleResult();
    }

    public Teacher getWithMinEXP(){

        EntityManager manager = factory.createEntityManager();

        String sqlRequest = "SELECT t FROM Teacher t WHERE t.experience = (SELECT MIN(t.experience) FROM Teacher t)" ;
        TypedQuery<Teacher> query = manager.createQuery(sqlRequest, Teacher.class);

        return query.getSingleResult();
    }

    public List<Teacher> getWithEXPMoreThan(Integer exp){

        if(exp != null) {
            EntityManager manager = factory.createEntityManager();

            String sqlRequest = "SELECT t FROM Teacher t WHERE t.experience >: exp" ;
            TypedQuery<Teacher> query = manager.createQuery(sqlRequest, Teacher.class);
            query.setParameter("exp", exp);
            return query.getResultList();

        }

        return null;
    }

    public List<Teacher> getWithEXPLessThan(Integer exp){
        if(exp != null) {
            EntityManager manager = factory.createEntityManager();

            String sqlRequest = "SELECT t FROM Teacher t WHERE t.experience <: exp" ;
            TypedQuery<Teacher> query = manager.createQuery(sqlRequest, Teacher.class);
            query.setParameter("exp", exp);
            return query.getResultList();

        }

        return null;
    }

}
