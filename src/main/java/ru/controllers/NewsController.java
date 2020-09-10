package ru.controllers;

import ru.services.NewsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/news")
    public ModelAndView main() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("news");
        mav.addObject("news", newsService.getLastNews());

        return mav;
    }
}