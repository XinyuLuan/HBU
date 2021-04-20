package com.hbu.backend.model.entity.course;

import com.hbu.backend.model.dto.course.SectionType;
import lombok.Data;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nationalized
    private String title;

    @Enumerated(EnumType.STRING)
    private SectionType sectionType;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Component> components;

    public void addOneComponent(Component component){
        if(components == null){
            components = new ArrayList<>();
        }
        components.add(component);
    }

    public boolean deleteOneComponent(Component component){
        if(!components.contains(component)){
            return false;
        }
        components.remove(component);
        return true;
    }

    public boolean updateComponent(Component component){
        int i = 0;
        for(; i < components.size(); i++){
            if(components.get(i).getId().equals(component.getId())){
                break;
            }
        }
        if(i == components.size()){
            return false;
        }
        components.set(i, component);
        return true;
    }
}
