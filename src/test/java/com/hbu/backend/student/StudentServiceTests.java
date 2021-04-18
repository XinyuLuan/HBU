//package com.hbu.backend.student;
//
//import com.hbu.backend.model.entity.Student;
//import com.hbu.backend.service.StudentService;
//import com.hbu.backend.utiliy.TestConstants;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Slf4j
//@SpringBootTest
//public class StudentServiceTests {
//    public final static String TAG_ = "StudentServiceTests";
//    private Student student;
//
//    @Autowired
//    private StudentService studentService;
//
//    @Test
//    public void testAddOneStudent() {
//        String firstName = TestConstants.FIRST_NAME;
//        student = studentService.addStudent(TestConstants.getStudent());
//        Assertions.assertEquals(firstName, student.getFirstName());
//        student = null;
//    }
//
////    @BeforeEach
////    public void beforeEach() {
////        student = TestConstants.getStudent();
////    }
//
//    @AfterEach
//    public void afterEach() {
//        studentService.deleteStudent(student);
//        student = null;
//    }
//
//    @Test
//    public void testDeleteOneStudent() {
//        student = TestConstants.getStudent();
//        boolean studentWasDeleted = false;
//        studentService.addStudent(student);
//
//        if (this.student != null) {
//            studentService.deleteStudent(student);
//            studentWasDeleted = true;
//        }
//        Assertions.assertTrue(studentWasDeleted);
//    }
//
//    @Test
//    public void testFindStudent() {
//        student = studentService.addStudent(TestConstants.getStudent());
//        Student foundStudent = studentService.findStudent(student.getId());
//        Assertions.assertTrue(foundStudent != null);
//    }
//
//    @Test
//    public void testFindAllStudents() {
//        Student studentOne = studentService.addStudent(TestConstants.getStudent());
//        Student studentTwo = studentService.addStudent(TestConstants.getStudent());
//
//        List<Student> expectedStudents = new ArrayList<>();
//
//        expectedStudents.add(studentOne);
//        expectedStudents.add(studentTwo);
//
//        List<Student> actualStudents = studentService.findAllStudents();
//
//        Assertions.assertEquals(expectedStudents.size(), actualStudents.size());
//    }
//
//    @Test
//    public void testUpdateStudent() {
//        student = studentService.addStudent(TestConstants.getStudent());
//        // update student id, first and lastnames
//        String updatedId = "updatedID";
//        String updatedFirstName = "updatedFirstname";
//        String updatedLastName = "updatedLastname";
//
//        student.setStudentId(updatedId);
//        student.setFirstName(updatedFirstName);
//        student.setLastName(updatedLastName);
//
//        // updated student should reflect the same id, first and last name as student
//        Student updatedStudent = studentService.updateStudent(student, student.getId());
//
//        Assertions.assertEquals(student.getStudentId(), updatedStudent.getStudentId());
//        Assertions.assertEquals(student.getFirstName(), updatedStudent.getFirstName());
//        Assertions.assertEquals(student.getLastName(), updatedStudent.getLastName());
//    }
//}
