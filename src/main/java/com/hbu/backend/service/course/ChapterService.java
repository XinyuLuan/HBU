package com.hbu.backend.service.course;

import com.hbu.backend.model.entity.course.Chapter;
import com.hbu.backend.repository.course.ChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterService {

    @Autowired
    ChapterRepository chapterRepository;

    public Chapter saveChapter(Chapter chapter){
        return chapterRepository.save(chapter);
    }

    public Chapter findChapter(Long id){
        return chapterRepository.findById(id).orElse(null);
    }

    public List<Chapter> findAllChapter(){
        return chapterRepository.findAll();
    }

    public Chapter updateChapter(Chapter newChapter, Long id){
        Chapter chapter = chapterRepository.findById(id).orElse(null);

        if(chapter == null){
            return null;
        }

        chapter.setSections(newChapter.getSections());
        return chapter;
    }

    public void deleteChapter(Long id){
        Chapter chapter = chapterRepository.findById(id).orElse(null);

        if(chapter == null){
            return;
        }

        chapterRepository.delete(chapter);
    }
}
