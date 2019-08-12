package com.spring.mvc.Springcast;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class LessonService {

    @Autowired
    private LessonRepository repository;

    public List<Lesson> get() {
        return repository.findAll();
    }

    public void store(Lesson lesson) {
        lesson.setSlug(this.convertToSlug(lesson.getTitle()));
        lesson.setCreated_at(this.now());
        lesson.setUpdated_at(this.now());
        repository.save(lesson);
    }

    public void update(int id, Lesson lessonRequest) {
        Lesson lesson = this.find(id);
        lesson.setTitle(lessonRequest.getTitle());
        lesson.setSlug(this.convertToSlug(lessonRequest.getTitle()));
        lesson.setCreated_at(lesson.getCreated_at());
        lesson.setUpdated_at(this.now());
    }

    public Lesson find(Integer id) {
        return repository.findById(id).get();
    }

    public void destroy(Integer id) {
        repository.deleteById(id);
    }

    private String convertToSlug(String string) {
        return string.toLowerCase().replaceAll(" ", "-");
    }

    private Instant now() {
        return Instant.now();
    }

}
