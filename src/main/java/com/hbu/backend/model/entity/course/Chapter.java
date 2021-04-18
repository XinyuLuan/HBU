package com.hbu.backend.model.entity.course;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Section> sections;

    public boolean addSection(Section section){
        return sections.add(section);
    }

    public boolean updateSection(Section section){
        int i = 0;
        for(; i < sections.size(); i++){
            if(sections.get(i).getId() == section.getId()){
                break;
            }
        }
        if(i == sections.size()){
            return false;
        }
        sections.set(i, section);
        return true;
    }

    public boolean deleteSection(Section section){
        return sections.remove(section);
    }
}
