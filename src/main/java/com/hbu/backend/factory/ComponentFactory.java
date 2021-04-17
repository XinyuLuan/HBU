package com.hbu.backend.factory;

import com.hbu.backend.model.dto.course.ComponentType;
import com.hbu.backend.model.entity.course.Component;
import com.hbu.backend.model.entity.course.component.MultipleChoiceComponent;
import com.hbu.backend.model.entity.course.component.FileComponent;
import com.hbu.backend.model.entity.course.component.TextComponent;

public class ComponentFactory {
    public Component getComponent(ComponentType componentType) {
        if(componentType == ComponentType.FILE) {
            return new FileComponent();
        }
        if(componentType == ComponentType.TEXT) {
            return new TextComponent();
        }
        if(componentType == ComponentType.MULTIPLE_CHOICE){
            return new MultipleChoiceComponent();
        }
        return null;
    }
}
