package mthree.com.fullstackschool.service;

import mthree.com.fullstackschool.dao.CourseDao;
import mthree.com.fullstackschool.model.Course;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CourseServiceTests {
    @Autowired
    private CourseServiceImpl courseService;

    @Test
    @DisplayName("Add new course successfully")
    void addNewCourseTest() {
        Course course = new Course();
        course.setCourseName("Intro to Testing");
        course.setCourseDesc("JUnit and Mockito basics");
        course.setTeacherId(1);

        Course saved = courseService.addNewCourse(course);

        assertNotNull(saved.getCourseId());
        assertEquals("Intro to Testing", saved.getCourseName());
        assertEquals("JUnit and Mockito basics", saved.getCourseDesc());
    }

    @Test
    @DisplayName("Find course by ID")
    void findCourseByIdTest() {
        // Assuming your data.sql contains at least one course with cid = 1
        Course course = courseService.getCourseById(1);
        assertNotNull(course);
        assertEquals(1, course.getCourseId());
        assertNotNull(course.getCourseName());
    }

    @Test
    @DisplayName("Find all courses")
    void getAllCoursesTest() {
        List<Course> courses = courseService.getAllCourses();
        assertNotNull(courses);
        assertTrue(courses.size() > 0, "Should have at least one course");
    }

    @Test
    @DisplayName("Update existing course")
    void updateCourseTest() {
        Course course = courseService.getCourseById(1);
        assertNotNull(course);

        course.setCourseName("Updated Course");
        course.setCourseDesc("Updated Description");
        Course updated = courseService.updateCourseData(course.getCourseId(), course);

        assertEquals("Updated Course", updated.getCourseName());
        assertEquals("Updated Description", updated.getCourseDesc());
    }

    @Test
    @DisplayName("Delete course by ID")
    void deleteCourseTest() {
        Course course = new Course();
        course.setCourseName("Temp Delete Test");
        course.setCourseDesc("Temp Desc");
        course.setTeacherId(1);

        Course added = courseService.addNewCourse(course);
        assertNotNull(added.getCourseId());

        courseService.deleteCourseById(added.getCourseId());

        // Try fetching again
        Course deleted = courseService.getCourseById(added.getCourseId());
        assertNull(deleted, "Deleted course should not be found");
    }
}
