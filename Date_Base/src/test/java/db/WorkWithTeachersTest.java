package db;

import models.Teacher;
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
public class WorkWithTeachersTest {

    WorkWithTeachers wwt;
    Connection connection;

    @Before
    public void setUp() throws Exception {
        connection = ConnectionFactory.getConnection();
        wwt = new WorkWithTeachers(connection);
    }

    @After
    public void tearDown() throws Exception {
        connection.close();
    }


    @Test
    public void createTeacher() throws Exception {
        Teacher teacher = new Teacher("Serhii", "Prunko", 4, 22);
        assertTrue(wwt.createTeacher(teacher));
        assertTrue(wwt.deleteTeacher(teacher));
    }

    @Test
    public void createTeacher_same() throws Exception {
        Teacher teacher = wwt.getTeacherById(1);
        assertFalse(wwt.createTeacher(teacher));
    }

    @Test
    public void getTeacherById() throws Exception {
        Teacher teacher = new Teacher("Irina", "Holovko", 1, 12);
        teacher.setId(1);
        assertEquals(teacher, wwt.getTeacherById(1));
    }

    @Test
    public void updateTeacherInfo() throws Exception {
        Teacher teacher = new Teacher("Irina", "Holovko", 1, 15);
        teacher.setId(1);
        assertTrue(wwt.updateTeacherInfo(teacher));
        teacher.setExperience(12);
        assertTrue(wwt.updateTeacherInfo(teacher));
    }

    @Test
    public void updateTeacherInfo_don_not_have_teacher() throws Exception {
        Teacher teacher = new Teacher("Irina", "Holo", 1, 12);
        teacher.setId(54);
        teacher.setExperience(15);
        assertFalse(wwt.updateTeacherInfo(teacher));
    }

    @Test
    public void getWithMaxEXP() throws Exception {
        Teacher teacher = new Teacher("Pasha", "Stepanenko", 5, 34);
        assertEquals(teacher, wwt.getWithMaxEXP());
    }

    @Test
    public void getWithMinEXP() throws Exception {
        Teacher teacher = new Teacher("Irina", "Holovko", 1, 12);
        assertEquals(teacher, wwt.getWithMinEXP());
    }

    @Test
    public void getWithEXPMoreThan() throws Exception {
        Teacher teacher = new Teacher("Pasha", "Stepanenko", 5, 34);
        List<Teacher> actual = wwt.getWithEXPMoreThan(30);
        assertTrue(actual.contains(teacher));
    }

    @Test
    public void getWithEXPLessThan() throws Exception {
        Teacher teacher = new Teacher("Irina", "Holovko", 1, 12);
        List<Teacher> acrual = wwt.getWithEXPLessThan(15);
        assertTrue(acrual.contains(teacher));
    }

}