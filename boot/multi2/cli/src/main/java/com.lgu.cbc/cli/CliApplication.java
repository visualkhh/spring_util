package com.lgu.cbc.cli;

import com.lgu.cbc.core.CbcApplicationBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.shell.standard.commands.Clear;
import org.springframework.util.StringUtils;

import java.util.Locale;

@Slf4j
@SpringBootApplication
@ShellComponent
public class CliApplication extends CbcApplicationBase {
    @Autowired
    CliApplication cliApplication;
    @Autowired
    private static ApplicationContext context;
    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(CliApplication.class);
        builder.build().addListeners(new ApplicationPidFileWriter());
        builder.run(args);
//        builder.context().getBean(CliApplication.class).loopStart();
//        String[] disabledCommands = {"--spring.shell.command.help.enabled=false"};
//        String[] fullArgs = StringUtils.concatenateStringArrays(args, disabledCommands);
//        SpringApplication.run(CliApplication.class, fullArgs);
//        Boot
    }

//    @Override
    public void loopRun() {
        log.info("CliApplication");
    }


    @ShellMethod("Translate text from one language to another.")
    public String translate(@ShellOption("a") String text) {
        // invoke service
        log.info(text);
        return "";
    }
    @ShellMethod("fdfdf")
    public void startloop() {
        cliApplication.loopStart();
    }
    @ShellMethod("Clear the screen, only better.")
    public void clcl() {
        log.info("clcl");
    }
//
    @ShellMethod("Displays greeting message to the user whose name is supplied")
    public String echo(@ShellOption({"-N", "--name"}) String name) {
        return String.format("Hello %s! You are running spring shell cli-demo.", name);
    }
//
//    @ShellMethod("Clear the screen, only better.")
//    public void clear() {
//        // ...
//    }
}
