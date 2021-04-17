package com.hbu.backend.service.course;

import com.hbu.backend.model.entity.course.Component;
import com.hbu.backend.model.entity.course.component.TextComponent;
import com.hbu.backend.repository.course.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.peer.ComponentPeer;
import java.util.List;

@Service
public class ComponentService {

    @Autowired
    private ComponentRepository componentRepository;

    /**
     * Create
     * @param component
     * @return
     */
    public Component save(Component component){
        return componentRepository.save(component);
    }

    /**
     * Read one
     * @param id
     * @return
     */
    public Component findComponent(Long id){
        return componentRepository.findById(id).orElse(null);
    }

    /**
     * Read all
     * @return
     */
    public List<Component> findAllComponents(){
        return componentRepository.findAll();
    }

    /**
     * update
     */
    public Component updateComponent(Component component, Long id){
        Component foundComponent = componentRepository.findById(id).orElse(null);

        if(foundComponent == null){
            return foundComponent;
        }

        foundComponent.setTitle(component.getTitle());
        foundComponent.setDescription(component.getDescription());
        return componentRepository.save(foundComponent);
    }

    /**
     * delete
     */
    public void deleteComponent(Component component){
        if(component != null){
            componentRepository.delete(component);
        }
    }
}
