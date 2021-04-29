package com.hbu.backend.controller.course;

import com.hbu.backend.model.dto.course.ChapterDTO;
import com.hbu.backend.model.dto.course.SectionDTO;
import com.hbu.backend.model.entity.course.Chapter;
import com.hbu.backend.model.entity.course.CourseModule;
import com.hbu.backend.model.entity.course.Section;
import com.hbu.backend.model.utility.DtoUtility;
import com.hbu.backend.service.course.ChapterService;
import com.hbu.backend.service.course.CourseModuleService;
import com.hbu.backend.service.course.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/chapter")
public class ChapterController {

    @Autowired
    CourseModuleService courseModuleService;
    @Autowired
    ChapterService chapterService;
    @Autowired
    SectionService sectionService;

    /**
     * Create a chapter
     * @param chapterDTO
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity<ChapterDTO> saveChapter(@RequestBody ChapterDTO chapterDTO){
        Chapter chapter = DtoUtility.toChapter(chapterDTO);
        chapter.setSections(new ArrayList<>());

        CourseModule courseModule = courseModuleService.findCourseModule(chapterDTO.getCourseModuleId());
        if(courseModule == null){
            return new ResponseEntity("Course Module " + chapterDTO.getCourseModuleId() + " NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        chapter.setCourseModule(courseModule);
        chapter = chapterService.saveChapter(chapter);

        courseModule.addChapter(chapter);
        courseModuleService.updateCourseModule(courseModule, courseModule.getId());

        return new ResponseEntity<ChapterDTO>(DtoUtility.toChapterDTO(chapter), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ChapterDTO> getChapter(@PathVariable Long id){
        Chapter chapter = chapterService.findChapter(id);

        if(chapter == null){
            return new ResponseEntity("Chapter " + id + " NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<ChapterDTO>(DtoUtility.toChapterDTO(chapter), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ChapterDTO>> getAllChapters(){
        List<Chapter> chapters = chapterService.findAllChapter();
        List<ChapterDTO> chapterDTOs = new ArrayList<>();
        for(Chapter chapter: chapters){
            chapterDTOs.add(DtoUtility.toChapterDTO(chapter));
        }
        return new ResponseEntity<List<ChapterDTO>>(chapterDTOs, HttpStatus.OK);
    }

    @PostMapping("/addSection/{chapterId}")
    public ResponseEntity<ChapterDTO> addSectionToChapter(@RequestBody SectionDTO sectionDTO, @PathVariable Long chapterId){
        Chapter chapter = chapterService.findChapter(chapterId);

        if(chapter == null){
            return new ResponseEntity("Chapter " + chapterId + " NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        Section section = DtoUtility.toSection(sectionDTO);
        if(chapter.getSections() == null){
            chapter.setSections(new ArrayList<>());
        }
        Section newSection = sectionService.saveSection(section);
        if(!chapter.addSection(newSection)){
            return new ResponseEntity("Chapter " + chapterId + " INSERT New Section FAILED", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ChapterDTO>(DtoUtility.toChapterDTO(chapterService.saveChapter(chapter)), HttpStatus.OK);
    }

    @PostMapping("/updateSection/{chapterId}/{sectionId}")
    public ResponseEntity<ChapterDTO> updateSectionToChapter(@RequestBody SectionDTO sectionDTO,
                                                             @PathVariable Long chapterId,
                                                             @PathVariable Long sectionId){
        Chapter chapter = chapterService.findChapter(chapterId);

        if(chapter == null){
            return new ResponseEntity("Chapter " + chapterId + " NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        Section section = sectionService.updateSection(DtoUtility.toSection(sectionDTO), sectionId);
        if(!chapter.updateSection(section)){
            return new ResponseEntity("Chapter " + chapterId + " UPDATE Section FAILED", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ChapterDTO>(DtoUtility.toChapterDTO(chapterService.saveChapter(chapter)), HttpStatus.OK);
    }

    @DeleteMapping("/deleteSection/{chapterId}/{sectionId}")
    public ResponseEntity<ChapterDTO> deleteSection(@PathVariable Long chapterId, @PathVariable Long sectionId){
//        Section section = sectionService.findSection(sectionId);
        Chapter chapter = chapterService.findChapter(chapterId);
        if(chapter == null){
            return new ResponseEntity("Chapter " + chapterId + " NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        Section section = sectionService.findSection(sectionId);
        if(section == null){
            return new ResponseEntity("Section " + sectionId + " DOES NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        if(!chapter.deleteSection(section)){
            return new ResponseEntity("Section " + sectionId + " DELETE FAILED", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(DtoUtility.toChapterDTO(chapterService.saveChapter(chapter)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteSection(@PathVariable Long id){
        Chapter chapter = chapterService.findChapter(id);

        if(chapter == null){
            return new ResponseEntity("Chapter " + id + " DOES NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        chapterService.deleteChapter(id);
        return new ResponseEntity("Chapter " + id + " HAVE BEEN DELETED", HttpStatus.OK);
    }
}
