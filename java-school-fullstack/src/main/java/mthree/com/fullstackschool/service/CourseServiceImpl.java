package mthree.com.fullstackschool.service;

import mthree.com.fullstackschool.dao.CourseDao;
import mthree.com.fullstackschool.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseServiceInterface {

    //YOUR CODE STARTS HERE

    @Autowired
    private CourseDao courseDao;

    //YOUR CODE ENDS HERE

    public List<Course> getAllCourses() {
        //YOUR CODE STARTS HERE

        return courseDao.getAllCourses();

        //YOUR CODE ENDS HERE
    }

    public Course getCourseById(int id) {
        //YOUR CODE STARTS HERE

        try {
            return courseDao.findCourseById(id);
        } catch (DataAccessException ex) {
            return null;
        }

        //YOUR CODE ENDS HERE
    }

    public Course addNewCourse(Course course) {
        //YOUR CODE STARTS HERE

        return courseDao.createNewCourse(course);

        //YOUR CODE ENDS HERE
    }

    public Course updateCourseData(int id, Course course) {
        //YOUR CODE STARTS HERE

        course.setCourseId(id);
        courseDao.updateCourse(course);
        return courseDao.findCourseById(id);
        //YOUR CODE ENDS HERE
    }

    public void deleteCourseById(int id) {
        //YOUR CODE STARTS HERE

        courseDao.deleteAllStudentsFromCourse(id);
        courseDao.deleteCourse(id);

        //YOUR CODE ENDS HERE
    }
}
