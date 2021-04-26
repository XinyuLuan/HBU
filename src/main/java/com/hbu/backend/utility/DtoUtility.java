package com.hbu.backend.utility;

import com.hbu.backend.model.dto.AdminDTO;
import com.hbu.backend.model.dto.course.*;
import com.hbu.backend.model.entity.Admin;
import com.hbu.backend.model.entity.Student;
import com.hbu.backend.model.entity.course.*;
import com.hbu.backend.model.entity.course.component.MultipleChoiceComponent;
import com.hbu.backend.model.entity.course.component.FileComponent;
import com.hbu.backend.model.entity.course.component.TextComponent;
import com.hbu.backend.service.course.ComponentService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.graph.Graph;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class DtoUtility {
    private static ComponentService componentService;

    public static CourseDTO toCourseDTO(Course course){
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCourseId(course.getId());
        courseDTO.setTitle(course.getTitle());
        courseDTO.setClassSubject(course.getClassSubject());
        courseDTO.setCourseNumber(course.getCourseNumber());
//        courseDTO.setInstructorId(course.getInstructor().getId());
//        courseDTO.setStartTime(course.getStartTime());
//        courseDTO.setEndTime(course.getEndTime());
        courseDTO.setCourseModuleIds(new ArrayList<>());
        for(CourseModule courseModule : course.getCourseModules()){
            courseDTO.getCourseModuleIds().add(courseModule.getId());
        }

        return courseDTO;
    }

    public static Course toCourseEntity(CourseDTO courseDTO){
        Course course = new Course();
        course.setId(courseDTO.getCourseId());
        course.setClassSubject(courseDTO.getClassSubject());
        course.setTitle(courseDTO.getTitle());
        course.setCourseNumber(courseDTO.getCourseNumber());
//        course.setStartTime(courseDTO.getStartTime());
//        course.setEndTime(courseDTO.getEndTime());
        return course;
    }

    public static AdminDTO toAdminDTO(Admin admin){
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setFirstName(admin.getFirstName());
        adminDTO.setLastName(admin.getLastName());
        adminDTO.setAdminId(admin.getAdminId());
        return adminDTO;
    }

    public static  Admin toAdmin(AdminDTO adminDTO){
        Admin admin = new Admin();
        admin.setFirstName(adminDTO.getFirstName());
        admin.setLastName(adminDTO.getLastName());
        return admin;
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
                log.info("DTO CONVERTION: toCourseModuleDTO-> " + chapter.getId().toString());
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
