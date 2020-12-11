package com.company.service.monitor.controllers.monitor;

import com.company.service.monitor.domain.monitor.UserCount;
import com.company.service.monitor.repository.monitor.UserCountRepository;
import com.company.service.monitor.schedule.ServiceScheduler;
import com.company.service.monitor.services.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping(MonitorController.URI_PREFIX)
public class MonitorController {
    public static final String URI_PREFIX = "/monitors";

    @Autowired
    UserCountRepository userCountRepository;

    @Autowired
    MonitorService monitorService;

    @Autowired
    ServiceScheduler serviceScheduler;


    @GetMapping(value = {"", "/"})
    public List<UserCount> index() {
        return userCountRepository.findAll();
    }

    @GetMapping(value = "/save")
    public UserCount save() {
        return userCountRepository.save(UserCount.builder().count(1).serviceName("z").build());
    }


//    @GetMapping(value = "/meas-use-email")
//    public String measUseEmail() throws Throwable {
//        serviceScheduler.mindcareMonitor();
//        return "good";
//    }

    //https://www.thymeleaf.org/doc/articles/springmvcaccessdata.html
    @GetMapping(value = "/meas-use")
    public ModelAndView measUse(@RequestParam(name = "day", defaultValue = "${projects.properties.monitor-day-count-size:10}") Integer day) {
        ModelAndView model = new ModelAndView("emails/meas-use");
        model.addObject("dayCountSize", day);
        monitorService.getDayCount(day).entrySet().stream().forEach(it -> model.addObject(it.getKey(), it.getValue()));
        return model;
    }

}
