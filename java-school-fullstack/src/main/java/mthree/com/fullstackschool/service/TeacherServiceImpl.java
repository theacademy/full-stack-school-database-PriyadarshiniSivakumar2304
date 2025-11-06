package mthree.com.fullstackschool.service;

import mthree.com.fullstackschool.dao.StudentDao;
import mthree.com.fullstackschool.dao.TeacherDao;
import mthree.com.fullstackschool.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherServiceInterface {

    //YOUR CODE STARTS HERE
    @Autowired
    private TeacherDao teacherDao;

    public void setTeacherDao(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    //YOUR CODE ENDS HERE

    public List<Teacher> getAllTeachers() {
        //YOUR CODE STARTS HERE

        return teacherDao.getAllTeachers();

        //YOUR CODE ENDS HERE
    }

    public Teacher getTeacherById(int id) {
        //YOUR CODE STARTS HERE

        try {
            return teacherDao.findTeacherById(id);
        } catch (DataAccessException ex) {
            Teacher notFound = new Teacher();
            notFound.setTeacherFName("Teacher Not Found");
            notFound.setTeacherLName("Teacher Not Found");
            return notFound;
        }

        //YOUR CODE ENDS HERE
    }

    public Teacher addNewTeacher(Teacher teacher) {
        //YOUR CODE STARTS HERE

        boolean firstNameBlank = (teacher.getTeacherFName() == null || teacher.getTeacherFName().isBlank());
        boolean lastNameBlank = (teacher.getTeacherLName() == null || teacher.getTeacherLName().isBlank());

        if (firstNameBlank && lastNameBlank) {
            // If both are blank, match your test's expected messages
            teacher.setTeacherFName("First Name blank, teacher NOT added");
            teacher.setTeacherLName("Last Name blank, teacher NOT added");
            return teacher;
        }

        if (firstNameBlank) {
            teacher.setTeacherFName("First Name blank, teacher NOT added");
            teacher.setTeacherLName("First Name blank, teacher NOT added");
            return teacher;
        }

        if (lastNameBlank) {
            teacher.setTeacherFName("Last Name blank, teacher NOT added");
            teacher.setTeacherLName("Last Name blank, teacher NOT added");
            return teacher;
        }

        // If both names are valid, proceed to add
        teacherDao.createNewTeacher(teacher);
        return teacher;

        //YOUR CODE ENDS HERE
    }

    public Teacher updateTeacherData(int id, Teacher teacher) {
        //YOUR CODE STARTS HERE

        if (id != teacher.getTeacherId()) {
            teacher.setTeacherFName("IDs do not match, teacher not updated");
            teacher.setTeacherLName("IDs do not match, teacher not updated");
            return teacher;
        }

        Teacher existingTeacher = teacherDao.findTeacherById(id);
        if (existingTeacher == null) {
            teacher.setTeacherFName("Teacher not found");
            teacher.setTeacherLName("Teacher not found");
            return teacher;
        }

        existingTeacher.setTeacherFName(teacher.getTeacherFName());
        existingTeacher.setTeacherLName(teacher.getTeacherLName());
        teacherDao.updateTeacher(existingTeacher);
        return existingTeacher;

        //YOUR CODE ENDS HERE
    }

    public void deleteTeacherById(int id) {
        //YOUR CODE STARTS HERE

        teacherDao.deleteTeacher(id);

        //YOUR CODE ENDS HERE
    }
}
