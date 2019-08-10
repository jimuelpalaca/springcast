package com.spring.mvc.Springcast;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {

    @Autowired
    private LessonRepository repository;

    public List<Lesson> get() {
        return repository.findAll();
    }

    public void store(Lesson lesson) {
        repository.save(lesson);
    }

    public Lesson find(Integer id) {
        return repository.findById(id).get();
    }

    public void destroy(Integer id) {
        repository.deleteById(id);
    }
}
