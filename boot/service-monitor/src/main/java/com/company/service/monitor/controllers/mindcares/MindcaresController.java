package com.company.service.monitor.controllers.mindcares;

import com.company.service.monitor.schedule.ServiceScheduler;
import com.company.service.monitor.services.MindcareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(MindcaresController.URI_PREFIX)
public class MindcaresController {
    public static final String URI_PREFIX = "/mindcares";

    @Autowired
    MindcareService mindcareService;

    @Autowired
    ServiceScheduler serviceScheduler;

    @GetMapping(value = "/total-email")
    public String totalEmail() throws Throwable {
        serviceScheduler.mindcareMonitor();
        return "good";
    }
    @GetMapping(value = "/total")
    public ModelAndView total() {
        ModelAndView model = new ModelAndView("emails/mindcares/total");
        mindcareService.getTotal().entrySet().stream().forEach(it -> model.addObject(it.getKey(), it.getValue()));
        return model;
    }

}
