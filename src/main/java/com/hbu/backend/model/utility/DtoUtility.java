package com.hbu.backend.model.utility;

import com.hbu.backend.model.dto.AdminDTO;
import com.hbu.backend.model.dto.UserDTO;
import com.hbu.backend.model.dto.course.*;
import com.hbu.backend.model.dto.InstructorDTO;
import com.hbu.backend.model.dto.StudentDTO;
import com.hbu.backend.model.entity.Admin;
import com.hbu.backend.model.entity.User;
import com.hbu.backend.model.entity.course.*;
import com.hbu.backend.model.entity.Instructor;
import com.hbu.backend.model.entity.Student;
import com.hbu.backend.model.entity.course.component.FileComponent;
import com.hbu.backend.model.entity.course.component.MultipleChoiceComponent;
import com.hbu.backend.model.entity.course.component.TextComponent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import java.util.List;

public class DtoUtility {

    /**
     * Course Conversion
     */
    public static CourseDTO toCourseDTO(Course course){
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCourseId(course.getId());
        courseDTO.setTitle(course.getTitle());
        courseDTO.setClassSubject(course.getClassSubject());
        courseDTO.setCourseNumber(course.getCourseNumber());
        courseDTO.setCourseModuleIds(new ArrayList<>());
        if(course.getCourseModules() != null){
            for(CourseModule courseModule : course.getCourseModules()){
                courseDTO.getCourseModuleIds().add(courseModule.getId());
            }
        }


        return courseDTO;
    }

    public static Course toCourseEntity(CourseDTO courseDTO){
        Course course = new Course();
        course.setId(courseDTO.getCourseId());
        course.setClassSubject(courseDTO.getClassSubject());
        course.setTitle(courseDTO.getTitle());
        course.setCourseNumber(courseDTO.getCourseNumber());
        course.setCourseModules(new ArrayList<>());
        return course;
    }

    /**
     * Admin Conversion
     */
    public static AdminDTO toAdminDTO(Admin admin){
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setFirstName(admin.getFirstName());
        adminDTO.setLastName(admin.getLastName());
        adminDTO.setId(admin.getId());
        return adminDTO;
    }

    public static Admin toAdmin(AdminDTO adminDTO){
        Admin admin = new Admin();
        admin.setLastName(adminDTO.getLastName());
        return admin;
    }

    /**
     * Student Conversion
     */
    public static StudentDTO toStudentDTO(Student student) {

        StudentDTO studentDTO = new StudentDTO();

        studentDTO.setId(student.getId());
        studentDTO.setStudentUniversityId(student.getStudentUniversityId());
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setEmail(student.getEmail());
        studentDTO.setUsername(student.getUsername());

        List<Long> courseModuleIds = new ArrayList<>();
        if(student.getCourseModules() != null){
            for(CourseModule courseModule : student.getCourseModules()){
                courseModuleIds.add(courseModule.getId());
            }
            studentDTO.setCourseModuleIds(courseModuleIds);
        }

        return studentDTO;

    }

    public static Student toStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setId(studentDTO.getId());
        student.setStudentUniversityId(studentDTO.getStudentUniversityId());
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setEmail(studentDTO.getEmail());
        student.setUsername(studentDTO.getEmail());
        student.setRoleType(studentDTO.getRoleType());

        return student;
    }

    /**
     * Instructor Conversion
     */
    public static InstructorDTO toInstructorDTO(Instructor instructor) {
        InstructorDTO instructorDTO = new InstructorDTO();
        instructorDTO.setId(instructor.getId());
        instructorDTO.setFirstName(instructor.getFirstName());
        instructorDTO.setLastName(instructor.getLastName());
        instructorDTO.setEmail(instructor.getEmail());
        instructorDTO.setRoleType(instructor.getRoleType());
        instructorDTO.setUsername(instructor.getUsername());
        List<Long> courseModuleIds = new ArrayList<>();
        for(CourseModule courseModule : instructor.getCourseModules()){
            courseModuleIds.add(courseModule.getId());
        }
        instructorDTO.setCourseModules(courseModuleIds);
        return instructorDTO;
    }

    public static Instructor toInstructor(InstructorDTO instructorDTO) {
        Instructor instructor = new Instructor();
        instructor.setId(instructorDTO.getId());
        instructor.setFirstName(instructorDTO.getFirstName());
        instructor.setLastName(instructorDTO.getLastName());
        instructor.setEmail(instructorDTO.getEmail());
        instructor.setUsername(instructorDTO.getUsername());
        instructor.setRoleType(instructorDTO.getRoleType());
        instructor.setCourseModules(new ArrayList<>());
        return instructor;
    }

    public static User toUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setRoleType(userDTO.getRoleType());
        return user;
    }

    /**
     * User conversion
     */
    public static UserDTO toUserDTO(User user) {
        UserDTO userDTO = setBasicUserFields(user);
        RoleType roleType = userDTO.getRoleType();

        if (roleType == RoleType.ADMIN) {
            return DtoUtility.toUserDTO((Admin) user);
        }
        if (roleType == RoleType.INSTRUCTOR) {
            return DtoUtility.toUserDTO((Instructor) user);
        }
        if (roleType == RoleType.STUDENT) {
            return DtoUtility.toUserDTO((Student) user);
        }
        return userDTO;
    }

    public static UserDTO toUserDTO(Admin admin) {
        UserDTO userDTO = setBasicUserFields(admin);
        return userDTO;
    }

    public static UserDTO toUserDTO(Instructor instructor) {
        UserDTO userDTO = setBasicUserFields(instructor);

        List<Long> courseModuleIds = new ArrayList<>();
        for(CourseModule courseModule : instructor.getCourseModules()){
            courseModuleIds.add(courseModule.getId());
        }
        return userDTO;
    }

    public static UserDTO toUserDTO(Student student) {
        UserDTO userDTO = setBasicUserFields(student);
//        userDTO.(student.getCourses());
        List<Long> courseModuleIds = new ArrayList<>();
        for(CourseModule courseModule : student.getCourseModules()){
            courseModuleIds.add(courseModule.getId());
        }
        userDTO.setStudentUniversityId(student.getStudentUniversityId());
        return userDTO;
    }

    /**
     * sets common fields found in parent class User
     */
    public static UserDTO setBasicUserFields(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setUsername(user.getUsername());
        userDTO.setRoleType(user.getRoleType());
        return userDTO;
    }



    /**
     * Components Conversion
     */

    public static ComponentDTO toComponentDTO(Component component){
        if(component.getComponentType() == ComponentType.TEXT){
            return DtoUtility.toComponentDTO((TextComponent) component);
        }
        if(component.getComponentType() == ComponentType.MULTIPLE_CHOICE){
            return DtoUtility.toComponentDTO((MultipleChoiceComponent) component);
        }

        return null;
    }

    public static ComponentDTO toComponentDTO(FileComponent fileComponent){
        ComponentDTO componentDTO = new ComponentDTO();
        componentDTO.setId(fileComponent.getId());
        componentDTO.setDescription(fileComponent.getDescription());
        componentDTO.setTitle(fileComponent.getTitle());
        componentDTO.setComponentType(fileComponent.getComponentType());

//        if(fileComponent.getComponentType().equals("file")){
//            componentDTO.setFileId();
//        }
        return componentDTO;
    }

    public static ComponentDTO toComponentDTO(MultipleChoiceComponent multipleChoiceComponent){
        ComponentDTO componentDTO = new ComponentDTO();
        componentDTO.setId(multipleChoiceComponent.getId());
        componentDTO.setDescription(multipleChoiceComponent.getDescription());
        componentDTO.setTitle(multipleChoiceComponent.getTitle());
        componentDTO.setComponentType(multipleChoiceComponent.getComponentType());
        if(multipleChoiceComponent.getComponentType() == ComponentType.MULTIPLE_CHOICE){
            componentDTO.setChoices(multipleChoiceComponent.getChoices());
        }
        return componentDTO;
    }

    public static ComponentDTO toComponentDTO(TextComponent textComponent){
        ComponentDTO componentDTO = new ComponentDTO();
        componentDTO.setId(textComponent.getId());
        componentDTO.setDescription(textComponent.getDescription());
        componentDTO.setTitle(textComponent.getTitle());
        componentDTO.setComponentType(textComponent.getComponentType());
        if(textComponent.getComponentType() == ComponentType.TEXT){
            componentDTO.setInputText(textComponent.getInputText());
        }
        return componentDTO;
    }

    public static Component toComponent(ComponentDTO componentDTO){
        Component component = createComponent(componentDTO);
        setCommonFieldsToComponent(component, componentDTO);
        return component;
    }

    public static Component createComponent(ComponentDTO componentDTO){

        if(componentDTO.getComponentType() == ComponentType.FILE){
//            FileComponent fileComponent = new FileComponent();
//            fileComponent.setFiles(componentDTO.getFileId());
        }
        if(componentDTO.getComponentType() == ComponentType.TEXT){
            TextComponent textComponent = new TextComponent();
            textComponent.setInputText(componentDTO.getInputText());
            return textComponent;
        }
        if(componentDTO.getComponentType() == ComponentType.MULTIPLE_CHOICE){
            MultipleChoiceComponent multipleChoiceComponent = new MultipleChoiceComponent();
            multipleChoiceComponent.setChoices(componentDTO.getChoices());
            return multipleChoiceComponent;
        }
        return null;
    }

    public static void setCommonFieldsToComponent(Component component, ComponentDTO componentDTO){
        component.setId(componentDTO.getId());
        component.setTitle(componentDTO.getTitle());
        component.setComponentType(componentDTO.getComponentType());
        component.setDescription(componentDTO.getDescription());
    }

    /**
     * Section Conversion
     */
    public static Section toSection(SectionDTO sectionDTO){
        Section section = new Section();
        section.setId(sectionDTO.getId());
        section.setSectionType(sectionDTO.getSectionType());
        section.setTitle(sectionDTO.getTitle());
//        for(Long id : sectionDTO.getComponentIds()){
//            log.info("Section DTO converter: " + id);
//            Component foundComponent = componentService.findComponent(id);
//            if(foundComponent == null){
//                continue;
//            }
//            section.getComponents().add(foundComponent);
//        }
        return section;
    }

    public static  SectionDTO toSectionDTO(Section section){
        SectionDTO sectionDTO = new SectionDTO();
        sectionDTO.setId(section.getId());
        sectionDTO.setTitle(section.getTitle());
        sectionDTO.setSectionType(section.getSectionType());

        sectionDTO.setComponentIds(new ArrayList<>());
        for(Component component : section.getComponents()){
            sectionDTO.getComponentIds().add(component.getId());
        }
        return sectionDTO;
    }

    /**
     * Chapter Conversion
     */
    public static ChapterDTO toChapterDTO(Chapter chapter){
        ChapterDTO chapterDTO = new ChapterDTO();
        chapterDTO.setId(chapter.getId());
        chapterDTO.setCourseModuleId(chapter.getCourseModule().getId());

        if(chapter.getSections() == null){
            return chapterDTO;
        }

        chapterDTO.setSectionIds(new ArrayList<>());
        for(Section section : chapter.getSections()){
            chapterDTO.getSectionIds().add(section.getId());
        }
        return chapterDTO;
    }

    public static  Chapter toChapter(ChapterDTO chapterDTO){
        Chapter chapter = new Chapter();
        chapter.setId(chapterDTO.getId());

//        chapterDTO.setSectionIds(new ArrayList<>());
//        for(Section section : chapterDTO.getSectionIds()){
//            chapterDTO.getSectionIds().add(section.getId());
//        }
        chapter.setSections(new ArrayList<>());
        return chapter;
    }

    /**
     * Class Module Conversion
     */
    public static CourseModuleDTO toCourseModuleDTO(CourseModule courseModule){
        CourseModuleDTO courseModuleDTO = new CourseModuleDTO();
        courseModuleDTO.setId(courseModule.getId());
        courseModuleDTO.setCourseId(courseModule.getCourse().getId());
        courseModuleDTO.setInstructorId(courseModule.getInstructor().getId());

        if(courseModule.getChapters() != null){
            courseModuleDTO.setChapterIds(new ArrayList<>());
            for(Chapter chapter : courseModule.getChapters()){
                courseModuleDTO.getChapterIds().add(chapter.getId());
            }
        }

        courseModuleDTO.setStudentIds(new HashSet<>());
        if(courseModule.getStudents() != null){
            for(Student student : courseModule.getStudents()){
                courseModuleDTO.getStudentIds().add(student.getId());
            }
        }

        courseModuleDTO.setGradeIds(new HashSet<>());
        if(courseModule.getGrades() != null){
            for(Grade grade : courseModule.getGrades()){
                courseModuleDTO.getGradeIds().add(grade.getId());
            }
        }

        return courseModuleDTO;
    }

    /**
     * Grade Conversion
     */
    public static GradeDTO toGradeDTO(Grade grade){
        GradeDTO gradeDTO = new GradeDTO();
        gradeDTO.setId(grade.getId());
        gradeDTO.setStudentId(grade.getStudent().getId());
        if(grade.getCourseModule() != null){
            gradeDTO.setCourseModuleId(grade.getCourseModule().getId());
        }
        gradeDTO.setGradeValue(grade.getGradeValue());
        return gradeDTO;
    }
}
