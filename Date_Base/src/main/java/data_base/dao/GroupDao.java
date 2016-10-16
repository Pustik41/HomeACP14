package data_base.dao;

import data_base.model.Group;
import data_base.model.GroupSubjects;
import data_base.model.Student;
import data_base.model.Subject;
import data_base.utils.ConnectionFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class GroupDao implements IDao<Group, Integer>{

    private EntityManagerFactory factory;

    public GroupDao(EntityManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<Group> getAll() {
        EntityManager manager = factory.createEntityManager();

        try {
            TypedQuery<Group> query = manager.createQuery("SELECT g FROM Group g ", Group.class);

            return query.getResultList();
        } finally {
            manager.close();
        }
    }

    @Override
    public boolean create(Group group) {

        if(group != null){

            EntityManager manager = factory.createEntityManager();
            EntityTransaction transaction = manager.getTransaction();

            try {
                transaction.begin();
                manager.persist(group);
                transaction.commit();

                return true;
            } catch (Exception e) {
                transaction.rollback();
                return false;
            } finally {
                manager.close();
            }
        }

        return false;
    }

    @Override
    public boolean update(Group group) {
        if(group != null){
            EntityManager manager = factory.createEntityManager();
            EntityTransaction transaction = manager.getTransaction();

            Group originGroup = manager.find(Group.class, group.getId());

            try {
                transaction.begin();
                originGroup.setName(group.getName());
                manager.merge(originGroup);
                transaction.commit();

                return true;
            } catch (Exception e) {
                transaction.rollback();
                return false;
            } finally {
                manager.close();
            }
        }

        return false;
    }

    @Override
    public Group getObjectById(Integer id) {
        if(id != null && id > 0){
            EntityManager manager = factory.createEntityManager();

            Group group = manager.find(Group.class, id);
            manager.close();

            return group;
        }

        return null;
    }

    @Override
    public boolean delete(Group group) {

        if(group != null){
            EntityManager manager = factory.createEntityManager();
            EntityTransaction transaction = manager.getTransaction();

            group = manager.find(Group.class, group.getId());

            try {
                transaction.begin();
                manager.remove(group);
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

    public List<Student> getStudentsListInThisGroup(Integer id){
       if(id != null && id > 0){

           EntityManager manager = factory.createEntityManager();

           try {

               Group group = manager.find(Group.class, id);
               return group.getStudentList();
           } finally {
               manager.close();
           }
       }

        return null;
    }

    public boolean assingGroupSubjects(List<Subject> subjects, Group group) {
        if(subjects != null && group!=null) {

            EntityManager manager = factory.createEntityManager();
            EntityTransaction transaction = manager.getTransaction();

            try {
                transaction.begin();

                subjects.stream().forEach(subject -> {
                    GroupSubjects gs = new GroupSubjects(group.getId(), subject.getId());
                    manager.persist(gs); });

                transaction.commit();
            } catch (Exception e){
                transaction.rollback();
                return false;
            } finally {
                manager.close();
            }

            return true;
        }

        return false;
    }
}
