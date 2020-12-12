package com.example.demo;

import com.example.demo.repository.DateDemoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@SpringBootApplication
@RestController
@Slf4j
public class DemoApplication {

    @Autowired
    DateDemoRepository dateDemoRepository;

    @GetMapping(value = {"", "/"})
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping(value = "/users")
    public ModelAndView users() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/users/index");
        modelAndView.addObject("datas", dateDemoRepository.findAll());
        modelAndView.addObject("it", dateDemoRepository.findByType(5));
        modelAndView.addObject("subit", dateDemoRepository.findByKHH());
        return modelAndView;
    }

    @GetMapping(value = "/hello")
    public ModelAndView hello(@RequestParam(name="data") Integer data, @RequestParam(name="divider") Integer divider) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/users/hello");
        modelAndView.addObject("data", data / divider);
        return modelAndView;
    }
    @GetMapping(value = "/sleep")
    public ModelAndView hello(@RequestParam(name="data") Integer data) throws InterruptedException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/users/sleep");
        modelAndView.addObject("data", data);
        Thread.sleep(data.longValue());
        return modelAndView;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
