package db;

import models.Group;
import models.Subject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.ConnectionFactory;

import java.sql.Connection;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Котято on 11.10.2016.
 */
public class WorkWithSubjectsTest {

    WorkWithSubjects wwsub;
    Connection connection;

    @Before
    public void setUp() throws Exception {
        connection = ConnectionFactory.getConnection();
        wwsub = new WorkWithSubjects(connection);
    }

    @After
    public void tearDown() throws Exception {
        connection.close();
    }

    @Test
    public void createSubject() throws Exception {
        Subject subject = new Subject("Physics", "Technical");
        assertTrue(wwsub.createSubject(subject));
        assertTrue(wwsub.deleteSubject(subject));
    }

    @Test
    public void createSubject_same() throws Exception {
        Subject subject = wwsub.getSubjectById(1);
        assertFalse(wwsub.createSubject(subject));
    }

    @Test
    public void getSubjectById() throws Exception {
        Subject subject = new Subject("Math", "Technical");
        assertEquals(subject, wwsub.getSubjectById(1));
    }

    @Test
    public void updateSubjectInfo() throws Exception {
        Subject subject = new Subject("Math", "Technical");
        subject.setSubject_id(1);
        subject.setDescription("Humanities");
        assertTrue(wwsub.updateSubjectInfo(subject));
        subject.setDescription("Technical");
        assertTrue(wwsub.updateSubjectInfo(subject));
    }

    @Test
    public void getListGroupsStudyingSubject() throws Exception {
        List<Group> expected = new WorkWithGroups(connection).getAllGropupsByCondition(null);
        assertEquals(expected, wwsub.getListGroupsStudyingSubject(1));
    }

    @Test
    public void getSubjectsListThatStudyAllGroups() throws Exception {
        List<Subject> expected = wwsub.getAllSubjectsByCondition(" WHERE subject_id < 4;");
        assertEquals(expected, wwsub.getSubjectsListThatStudyAllGroups());
    }

    @Test
    public void getSubjectsByDescription() throws Exception {
        List<Subject> expected = wwsub.getAllSubjectsByCondition(" WHERE description ='Technical';");
        assertEquals(expected, wwsub.getSubjectsByDescription("Technical"));
    }

    @Test
    public void getAvgMarkOnSubjectAmongAllStudents() throws Exception {
        Integer expected = 85;
        assertEquals(expected, wwsub.getAvgMarkOnSubjectAmongAllStudents(1));
    }

    @Test
    public void getAvgMarkOnSubjectByGroup() throws Exception {
        Integer expected = 88;
        assertEquals(expected, wwsub.getAvgMarkOnSubjectByGroup(1, 1));
    }

}