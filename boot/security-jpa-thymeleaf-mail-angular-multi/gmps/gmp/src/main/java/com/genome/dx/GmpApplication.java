package com.genome.dx;

import com.genome.dx.core.domain.Term;
import com.genome.dx.core.model.phenoType.PhenoTypeTree;
import com.genome.dx.core.model.phenoType.PhenoTypeTreeForBootstrapTreeView;
import com.genome.dx.wcore.service.MetasService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Map;

@Slf4j
@SpringBootApplication
@EnableAsync
@EnableCaching
public class GmpApplication {


    @Value("${spring.application.name}")
    String applicationName;

    @Autowired
    MetasService metasService;

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(GmpApplication.class);
        builder.build().addListeners(new ApplicationPidFileWriter());
        builder.run(args);
    }

    @EventListener
    public void applicationStartedEvent(ApplicationStartedEvent applicationStartedEvent) {
        Map<Long, Term> termAllMap = metasService.findTermAll();
        List<PhenoTypeTreeForBootstrapTreeView> phenoType = metasService.findPhenoTypeForBootStrapTreeView();
        log.debug("applicationStartedEvent done!!");
    }

    @PostConstruct
    public void onStart() {
        log.info("START {}", applicationName);
    }

    @PreDestroy
    public void onExit() {
        log.info("EXIT {}", applicationName);
    }
}
