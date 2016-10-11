package db;

import models.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.ConnectionFactory;

import java.sql.Connection;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by Котято on 10.10.2016.
 */
public class WorkWithStudentsTest {

    WorkWithStudents wws;
    Connection connection;

    @Before
    public void setUp() throws Exception {
        connection  = ConnectionFactory.getConnection();
        wws = new WorkWithStudents(connection);
    }

    @After
    public void tearDown() throws Exception {
        connection.close();
    }

    @Test
    public void getAllStudentsByCondition() throws Exception {
        Student student1 = new Student("Alex", "Browko", 1);
        Student student2 = new Student("Dima", "Vasiliv", 2);
        Student student3 = new Student("Oleg", "Vorona", 2);
        Student student4 = new Student("Katja", "Kirko", 1);

        List<Student> expected = new ArrayList<>();
        Collections.addAll(expected, student1, student2, student3, student4);

        assertEquals(expected, wws.getAllStudentsByCondition(null));
    }

    @Test
    public void createStudent() throws Exception {
        Student student = new Student("Artem", "Butko", 1);
        assertTrue(wws.createStudent(student));
        assertTrue(wws.deleteStudent(student));
    }

    @Test
    public void createStudent_same() throws Exception {
        Student student = new Student("Alex", "Browko", 1);
        assertFalse(wws.createStudent(student));
    }


    @Test
    public void getStudentById() throws Exception {
        Student student = new Student("Alex", "Browko", 1);
        student.setId(1);
        assertEquals(student, wws.getStudentById(student.getId()));
    }

    @Test
    public void updateStudentInfo() throws Exception {
        Student student = new Student("Katja", "Kirko", 2);
        student.setId(4);
        assertTrue(wws.updateStudentInfo(student));
        student.setGroup_id(1);
        assertTrue(wws.updateStudentInfo(student));
    }

    @Test
    public void rateStudentBySubject() throws Exception {
        List<Integer> subject_id = new WorkWithGroups(connection).getGroupSubjects_id(1);
        Map<Integer, Integer> studentProgress = new HashMap<>();
        Student student = new Student("Artem", "Kirko", 1);
        wws.createStudent(student);
        student.setId(wws.getAllStudentsByCondition(" WHERE s.firstname='" + student.getFirstName() +"' " +
                "AND s.lastname='" + student.getLastName() + "' AND" +
                " s.group_id=" + student.getGroup_id()).get(0).getId());


        for (Integer subject: subject_id) {
            studentProgress.put(subject, ((int) (Math.random() * 35) + 65));

        }

        assertTrue(wws.rateStudentBySubject(studentProgress, student.getId()));
        assertTrue(wws.deleteStudent(student));
    }
}