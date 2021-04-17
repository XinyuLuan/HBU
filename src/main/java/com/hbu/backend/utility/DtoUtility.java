package com.hbu.backend.utility;

import com.hbu.backend.model.dto.AdminDTO;
import com.hbu.backend.model.dto.course.ComponentType;
import com.hbu.backend.model.dto.course.CourseDTO;
import com.hbu.backend.model.dto.course.ComponentDTO;
import com.hbu.backend.model.entity.Admin;
import com.hbu.backend.model.entity.course.Component;
import com.hbu.backend.model.entity.course.Course;
import com.hbu.backend.model.entity.course.component.MultipleChoiceComponent;
import com.hbu.backend.model.entity.course.component.FileComponent;
import com.hbu.backend.model.entity.course.component.TextComponent;


public class DtoUtility {

    public static CourseDTO toCourseDTO(Course course){
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCourseId(course.getCourseId());
        courseDTO.setTitle(course.getTitle());
        courseDTO.setClassSubject(course.getClassSubject());
        courseDTO.setCourseNumber(course.getCourseNumber());
//        courseDTO.setInstructorId(course.getInstructor().getId());
//        courseDTO.setStartTime(course.getStartTime());
//        courseDTO.setEndTime(course.getEndTime());
        return courseDTO;
    }

    public static Course toCourseEntity(CourseDTO courseDTO){
        Course course = new Course();
        course.setCourseId(courseDTO.getCourseId());
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



//    public static TextComponent toTextComponent(TextComponentDTO textComponentDTO){
//        TextComponent textComponent = new TextComponent();
//        textComponent.setId(textComponentDTO.getId());
//        textComponent.setTitle(textComponentDTO.getTitle());
//        textComponent.setDescription(textComponentDTO.getDescription());
//        textComponent.setInputText(textComponentDTO.getInputText());
//        textComponent.setComponentType(textComponentDTO.getComponentType());
//        return textComponent;
//    }
//    public static TextComponentDTO toTextComponentDTO(TextComponent textComponent){
//        TextComponentDTO textComponentDTO = new TextComponentDTO();
//        textComponentDTO.setId(textComponent.getId());
//        textComponentDTO.setDescription(textComponent.getDescription());
//        textComponentDTO.setTitle(textComponent.getTitle());
//        textComponentDTO.setInputText(textComponent.getInputText());
//        textComponentDTO.setComponentType(textComponent.getComponentType());
//        return textComponentDTO;
//    }

//    public static TextComponent toTextComponent(ComponentDTO componentDTO){
//        TextComponent textComponent = new TextComponent();
//        textComponent.setId(componentDTO.getId());
//        textComponent.setTitle(componentDTO.getTitle());
//        textComponent.setDescription(componentDTO.getDescription());
//        textComponent.setInputText(componentDTO.getInputText());
//        textComponent.setComponentType(componentDTO.getComponentType());
//        return textComponent;
//    }
}
