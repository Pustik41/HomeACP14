package db;

import models.Group;
import models.Student;
import models.Subject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.ConnectionFactory;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Котято on 10.10.2016.
 */
public class WorkWithGroupsTest {

    Connection connection;
    WorkWithGroups wwg;
    String sqlRequest;

    @Before
    public void setUp() throws Exception {
        connection = ConnectionFactory.getConnection();
        wwg = new WorkWithGroups(connection);
    }

    @After
    public void tearDown() throws Exception {
        connection.close();
    }

    @Test
    public void getAllGropupsByCondition_sqlRequest_null() throws Exception {
        sqlRequest = null;
        int id = 1;
        List<Group> expected = new ArrayList<>();
        expected.add(new Group("IAP101"));
        expected.add(new Group("IKIT101"));

        for (Group group:
             expected) {
            group.setGroup_id(id);
            id++;
        }

        assertEquals(expected, wwg.getAllGropupsByCondition(sqlRequest));
    }

    public void getAllGropupsByCondition_sqlRequest_group_id_1() throws Exception {
        sqlRequest = " WHERE group_id=1";
        List<Group> expected = new ArrayList<>();
        expected.add(new Group("IAP101"));
        expected.get(0).setGroup_id(1);

        assertTrue(expected.containsAll(wwg.getAllGropupsByCondition(sqlRequest)));
    }

    @Test
    public void createGroup() throws Exception {
        Group group = new Group("IKIT102");
        assertTrue(wwg.createGroup(group));
        assertTrue(wwg.deleteGroup(group));
    }

    @Test
    public void createGroup_with_same_info() throws Exception {
        Group group = new Group("IAP101");

        assertFalse(wwg.createGroup(group));
    }

    @Test
    public void getGroupById() throws Exception {
        Group group = new Group("IAP101");
        group.setGroup_id(1);

        assertEquals(group, wwg.getGroupById(1));
    }

    @Test
    public void getGroupById_id_equals_0() throws Exception {
        assertEquals(null, wwg.getGroupById(0));
    }

    @Test
    public void updateGroupInfo() throws Exception {
        Group group = new Group ("GMI102");
        group.setGroup_id(2);

        assertTrue(wwg.updateGroupInfo(group));
        group.setName("IKIT101");
        wwg.updateGroupInfo(group);


    }

    @Test
    public void getStudentsListInThisGroup() throws Exception {
        List<Student> expected = new WorkWithStudents(connection).getAllStudentsByCondition(" WHERE s.group_id=1");
        assertEquals(expected, wwg.getStudentsListInThisGroup(1));
    }

    @Test
    public void getStudentsListInThisGroup_where_wrong_id() throws Exception {
        assertEquals(null, wwg.getStudentsListInThisGroup(0));
    }

    @Test
    public void assingGroupSubjects() throws Exception {
        Group group = new Group("IKIT102");
        wwg.createGroup(group);
        int group_id = wwg.getAllGropupsByCondition(" WHERE name_group='IKIT102'").get(0).getGroup_id();
        List<Subject> subjects = new WorkWithSubjects(connection).getAllSubjectsByCondition(
                " WHERE subject_id < 5");

        assertTrue(wwg.assingGroupSubjects(subjects, group_id));
        wwg.deleteGroup(group);

    }

    @Test
    public void assingGroupSubjects_list_null() throws Exception {
        List<Subject> subjects = null;

        assertFalse(wwg.assingGroupSubjects(subjects, 0));
    }

}