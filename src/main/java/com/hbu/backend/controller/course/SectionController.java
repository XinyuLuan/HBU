package com.hbu.backend.controller.course;

import com.hbu.backend.model.dto.course.ComponentDTO;
import com.hbu.backend.model.dto.course.ComponentType;
import com.hbu.backend.model.dto.course.SectionDTO;
import com.hbu.backend.model.entity.course.Component;
import com.hbu.backend.model.entity.course.Section;
import com.hbu.backend.model.utility.DtoUtility;
import com.hbu.backend.service.course.ComponentService;
import com.hbu.backend.service.course.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/section")
public class SectionController {
    @Autowired
    SectionService sectionService;
    @Autowired
    ComponentService componentService;

    @PostMapping("/add")
    public ResponseEntity<SectionDTO> saveSection(@RequestBody SectionDTO sectionDTO){
        Section section = DtoUtility.toSection(sectionDTO);
        section.setComponents(new ArrayList<>());
        section = sectionService.saveSection(section);
        return new ResponseEntity<SectionDTO>(DtoUtility.toSectionDTO(section), HttpStatus.OK);
    }

    @PostMapping("/addcomponent/{sectionId}")
    public ResponseEntity<SectionDTO> saveComponent(@RequestBody ComponentDTO componentDTO, @PathVariable Long sectionId){
        Section section = sectionService.findSection(sectionId);

        if(section == null){
            return new ResponseEntity("Section" + sectionId + "DOES NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        Component component = DtoUtility.toComponent(componentDTO);
        componentService.save(component);
        section.addOneComponent(component);

        return new ResponseEntity<SectionDTO>(DtoUtility.toSectionDTO(sectionService.saveSection(section)), HttpStatus.OK);
    }

    @DeleteMapping("/deletecomponent/{sectionId}/{componentId}")
    public ResponseEntity<SectionDTO> deleteComponent(@PathVariable Long sectionId, @PathVariable Long componentId){
        Section section = sectionService.findSection(sectionId);
        if(section == null){
            return new ResponseEntity("Section " + sectionId + " DOES NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        Component component = componentService.findComponent(componentId);
        if(component == null){
            return new ResponseEntity("Component " + componentId + " DOES NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        if(!section.deleteOneComponent(component)){
            return new ResponseEntity("Component " + componentId + " DELETE FAILED", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(DtoUtility.toSectionDTO(sectionService.saveSection(section)), HttpStatus.OK);
    }

    @PutMapping("/updatecomponent/{sectionId}/{componentId}")
    public ResponseEntity<SectionDTO> editComponent(@PathVariable Long sectionId, @PathVariable Long componentId, @RequestBody Component editComponent){
        Section section = sectionService.findSection(sectionId);
        if(section == null){
            return new ResponseEntity("Section " + sectionId + " DOES NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        Component component = componentService.findComponent(componentId);
        if(component == null){
            return new ResponseEntity("Component " + componentId + " DOES NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        if(!section.updateComponent(editComponent)){
            return new ResponseEntity("Component " + componentId + " UPDATE FAILED", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<SectionDTO>(DtoUtility.toSectionDTO(section), HttpStatus.OK);
    }

    @PutMapping("/update/{sectionId}")
    public ResponseEntity<SectionDTO> updateComponent(@RequestBody SectionDTO sectionDTO, @PathVariable Long sectionId){
        Section section = sectionService.findSection(sectionId);

        if(section == null){
            return new ResponseEntity("Section " + sectionId + " DOES NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        /* the if statement is not nessary, since the relationship from section to components is (one to many),
        so after we assign component to a secton, it's impossible to assign a component to another seciton*/
        if(sectionDTO.getComponentIds() != null){
            section.setComponents(new ArrayList<>());
            for(Long id : sectionDTO.getComponentIds()){
                Component component = componentService.findComponent(id);
                if(component == null){
                    return new ResponseEntity("Component " + id + " DOES NOT EXIST", HttpStatus.BAD_REQUEST);
                }
                section.addOneComponent(component);
            }
        }
        section.setTitle(sectionDTO.getTitle());
        section.setSectionType(sectionDTO.getSectionType());

//        section = sectionService.saveSection(section);
        return new ResponseEntity<SectionDTO>(DtoUtility.toSectionDTO(sectionService.saveSection(section)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SectionDTO> getSection(@PathVariable Long id){
        Section section = sectionService.findSection(id);

        if(section == null){
            return new ResponseEntity("Section " + id + " DOES NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<SectionDTO>(DtoUtility.toSectionDTO(section), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SectionDTO>> getAllSection(){
        List<Section> sections = sectionService.findAllSections();

        List<SectionDTO> sectionDTOs = new ArrayList<>();
        for(Section section : sections){
            sectionDTOs.add(DtoUtility.toSectionDTO(section));
        }

        return new ResponseEntity<List<SectionDTO>>(sectionDTOs, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteSection(@PathVariable Long id){
        Section section = sectionService.findSection(id);

        if(section == null){
            return new ResponseEntity("Section " + id + " DOES NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        sectionService.deleteSection(id);
        return new ResponseEntity("Section " + id + " HAVE BEEN DELETED", HttpStatus.OK);
    }


}
