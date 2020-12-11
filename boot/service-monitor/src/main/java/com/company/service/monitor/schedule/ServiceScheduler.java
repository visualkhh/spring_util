package com.company.service.monitor.schedule;

import com.company.service.monitor.services.MailService;
import com.company.service.monitor.services.MindcareService;
import com.company.service.monitor.services.MonitorService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class ServiceScheduler {


    @Autowired
    MailService javaMailSender;

    @Autowired
    @Qualifier("elasticsearchClient")
    RestHighLevelClient restHighLevelClient;

    @Autowired
    SpringTemplateEngine springTemplateEngine;

    @Autowired
    MonitorService monitorService;

    @Autowired
    MindcareService mindcareService;

    @Value("${projects.properties.monitor-day-count-size:10}")
    Integer monitorDayCountSize;

    @Value("${projects.properties.email-serviceteam}")
    String emailServiceteam;

    @Value("${projects.properties.email-omnifit}")
    String emailOmnifit;

    @Scheduled(cron = "1 1 8 * * *")
    public void mindcareMonitor() throws Throwable {
        String title = String.format("[모니터 서비스통계] %s %s", new SimpleDateFormat("yyyy-MM-dd").format(new Date()), "현황 공유 건");
        Context context = new Context();
        context.setVariable("dayCountSize", monitorDayCountSize);
        mindcareService.getTotal().entrySet().stream().forEach(it -> context.setVariable(it.getKey(), it.getValue()));
        String html = springTemplateEngine.process("emails/mindcares/total", context);
        javaMailSender.sendHtml(emailOmnifit, title, html);
    }
}
