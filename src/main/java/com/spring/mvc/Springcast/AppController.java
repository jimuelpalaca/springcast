package com.spring.mvc.Springcast;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AppController {
    @Autowired
    private LessonService lessonService;

    @RequestMapping("/")
    public String home() {
        return "redirect:/lessons";
    }

    @RequestMapping("/lessons")
    public String index(Model model) {
        List<Lesson> lessons = lessonService.get();
        model.addAttribute("lessons", lessons);

        return "index";
    }

    @RequestMapping("/lessons/create")
    public String create(Model model) {
        Lesson lesson = new Lesson();
        model.addAttribute("lesson", lesson);

        return "create";
    }

    @RequestMapping(value = "/lessons", method = RequestMethod.POST)
    public String store(@ModelAttribute("lesson") Lesson lesson) {
        lessonService.store(lesson);

        return "redirect:/lessons";
    }

    @RequestMapping("lessons/{id}/edit")
    public ModelAndView edit(@PathVariable(name = "id") int id) {
        ModelAndView editView = new ModelAndView("edit");
        Lesson lesson = lessonService.find(id);
        editView.addObject("lesson", lesson);

        return editView;
    }

    @RequestMapping(value = "lessons/{id}", method = RequestMethod.PUT)
    public String update(@PathVariable(name = "id") int id, @ModelAttribute("lesson") Lesson lessonAttribute) {
        lessonService.update(id, lessonAttribute);

        return "redirect:/lessons/" + id + "/edit";
    }

    @RequestMapping("/lessons/{id}/delete")
    public String destroy(@PathVariable(name = "id") int id) {
        lessonService.destroy(id);

        return "redirect:/lessons";
    }
}
