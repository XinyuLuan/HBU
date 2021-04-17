package com.hbu.backend.controller.course;

import com.hbu.backend.factory.ComponentFactory;
import com.hbu.backend.model.dto.AdminDTO;
import com.hbu.backend.model.dto.course.ComponentDTO;
import com.hbu.backend.model.entity.Admin;
import com.hbu.backend.model.entity.course.Component;
import com.hbu.backend.service.course.ComponentService;
import com.hbu.backend.utility.DtoUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/component")
public class ComponentController {
//    @Autowired
//    private TextComponentService textComponentService;
    final String _ErrorMessage = "Component ";

    @Autowired
    private ComponentService componentService;
//    @Autowired
//    ComponentFactory componentFactory;

    @PostMapping("/add")
    public ResponseEntity<ComponentDTO> addTextComponent(@RequestBody ComponentDTO componentDTO){
        Component component = new ComponentFactory().getComponent(componentDTO.getComponentType());

        component = DtoUtility.toComponent(componentDTO);
        component = componentService.save(component);
//        ComponentDTO newComponentDTO = new ComponentDTO();
//        if(componentDTO.getComponentType().equals(ComponentType.TEXT)){
//            newComponentDTO = DtoUtility.toComponentDTO((TextComponent) component);
//        }
//        if(componentDTO.getComponentType() == ComponentType.MULTIPLE_CHOICE){
//            newComponentDTO = DtoUtility.toComponentDTO((MultipleChoiceComponent) component);
//        }

        ComponentDTO newComponentDTO = DtoUtility.toComponentDTO(component);
        return new ResponseEntity<ComponentDTO>(newComponentDTO, HttpStatus.OK);
    }

    @GetMapping("/{componentId}")
    public ResponseEntity<ComponentDTO> getComponent(@PathVariable Long componentId){
        Component component = componentService.findComponent(componentId);

        if(component == null){
            return new ResponseEntity(_ErrorMessage + componentId + "NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<ComponentDTO>(DtoUtility.toComponentDTO(component), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ComponentDTO>> getAllComponents(){
        List<Component> components = componentService.findAllComponents();

        List<ComponentDTO> componentDTOs = new ArrayList<>();
        for(Component component : components){
            componentDTOs.add(DtoUtility.toComponentDTO(component));
        }

        return new ResponseEntity<List<ComponentDTO>>(componentDTOs, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComponentDTO> updateComponent(@RequestBody ComponentDTO componentDTO, @PathVariable Long id){
        Component component = componentService.updateComponent(DtoUtility.toComponent(componentDTO), id);

        if(component == null){
            return new ResponseEntity(_ErrorMessage + id + " NOT EXIST", HttpStatus.BAD_REQUEST);
        }
        if(component.getComponentType() != componentDTO.getComponentType()){
            return new ResponseEntity(_ErrorMessage + id + " TYPE NOT MATCH", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<ComponentDTO>(DtoUtility.toComponentDTO(component), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteAdmin(@PathVariable Long id){
        Component component = componentService.findComponent(id);

        if(component == null){
            return new ResponseEntity(_ErrorMessage + id + " NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        componentService.deleteComponent(component);
        return new ResponseEntity("Deleted Component " + id, HttpStatus.OK);
    }
}
