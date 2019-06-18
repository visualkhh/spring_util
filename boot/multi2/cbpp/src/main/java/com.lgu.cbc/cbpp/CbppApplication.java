package com.lgu.cbc.cbpp;

import com.lgu.cbc.cbpp.service.TestService;
import com.lgu.cbc.core.CbcApplicationBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.ApplicationPidFileWriter;

import java.sql.SQLException;

@SpringBootApplication
@Slf4j
public class CbppApplication extends CbcApplicationBase {
    @Autowired
    TestService testService;
    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(CbppApplication.class);
        builder.build().addListeners(new ApplicationPidFileWriter());
        builder.run(args);
        builder.context().getBean(CbppApplication.class).start();
    }

    @Override
    public void loopRun() {
        log.info("CbppApplication");
        testService.insert("유현");
        testService.findAll().stream().forEach(it -> log.info(it.toString()));



//        try {
//            Message msg = this.getMessage();
//
//            if (msg != null) {
//                this.processMessageCell(msg.msgSeq, msg.sendDiv);
//                this.updateMessage(msg.msgSeq);
//                logger.info("msg_seq:" + msg.msgSeq);
//            }
//        } catch (SQLException e) {
//            TloLogger.w(Worker.reqTime, "db", "2000");
//            e.printStackTrace();
//        }
    }
}
