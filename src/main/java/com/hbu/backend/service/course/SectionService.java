package com.hbu.backend.service.course;

import com.hbu.backend.model.entity.course.Component;
import com.hbu.backend.model.entity.course.Section;
import com.hbu.backend.repository.course.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class SectionService {

    @Autowired
    SectionRepository sectionRepository;

    /**
     * Create
     * @param section
     * @return
     */
    public Section saveSection(Section section){
        return sectionRepository.save(section);
    }

    /**
     * Read
     * @param id
     * @return
     */
    public Section findSection(Long id){
        return sectionRepository.findById(id).orElse(null);
    }

    public List<Section> findAllSections(){
        return sectionRepository.findAll();
    }

    /**
     * Update
     * @param section
     * @param id
     * @return
     */
    public Section updateSection(Section section, Long id){
        Section foundSection = sectionRepository.findById(id).orElse(null);

        if(foundSection == null){
            return null;
        }

        foundSection.setTitle(section.getTitle());
        foundSection.setComponents(section.getComponents());

        return sectionRepository.save(foundSection);
    }

    public Section addComponent(Component newComponent, Long id){
        Section foundSection = sectionRepository.findById(id).orElse(null);

        if(foundSection == null){
            return null;
        }

        foundSection.getComponents().add(newComponent);
        return sectionRepository.save(foundSection);
    }

    /**
     * Delete ...
     */
    public void deleteSection(Long id){
        Section section = sectionRepository.findById(id).orElse(null);

        if(section == null){
            return;
        }

        sectionRepository.delete(section);
    }
}
