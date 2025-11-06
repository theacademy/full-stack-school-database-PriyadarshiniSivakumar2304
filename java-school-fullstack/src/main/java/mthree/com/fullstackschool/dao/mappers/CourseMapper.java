package mthree.com.fullstackschool.dao.mappers;

import mthree.com.fullstackschool.model.Course;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseMapper implements RowMapper<Course> {
    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
        //YOUR CODE STARTS HERE

        Course course = new Course();
        course.setCourseId(rs.getInt("cid"));
        course.setCourseName(rs.getString("courseCode"));
        course.setCourseDesc(rs.getString("courseDesc"));
// teacherId can be null in DB; getInt returns 0 when NULL so check wasNull
        int teacherId = rs.getInt("teacherId");
        if (rs.wasNull()) {
            course.setTeacherId(0);
        } else {
            course.setTeacherId(teacherId);
        }
        return course;

        //YOUR CODE ENDS HERE
    }
}
