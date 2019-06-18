package com.lgu.cbc.cgpp;

import com.lgu.cbc.core.CbcApplicationBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.SQLException;

@SpringBootApplication
@Slf4j
public class CgppApplication extends CbcApplicationBase {
    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(CbcApplicationBase.class);
        builder.build().addListeners(new ApplicationPidFileWriter());
        builder.run(args);
        builder.context().getBean(CbcApplicationBase.class).start();
    }

    @Override
    public void loopRun() {
        log.info("CbcApplicationBase");
//        try {
//            this.restoreSendCell();
//            this.restoreMessageList();
//
//            if (Main.clientList.size() == 0) {
//                this.startClient();
//            }
//
//            if (this.cm == null) {
//                this.cm = new ClientManager(this);
//                this.cm.boot();
//            }
//
//            while (true) {
//                this.reqTime = TloLogger.getReqTime();
//                this.checkSendCell();
//                this.checkMsgCell();
//
//                try {
//                    this.processSendCell();
//                    this.deleteOldMessage();
//
//                    try {
//                        Thread.sleep(Config.DB_POLLING_TIME);
//                    } catch (InterruptedException e) {
//                    }
//                } catch (InterruptedException e1) {
//                }
//
//                System.err.println("OK");
//
//                if (this.isStop) {
//                    return;
//                }
//            }
//        } catch (SQLException ex) {
//            TloLogger.w(this.reqTime, "db", "2000");
//            ex.printStackTrace();
//
//            try {
//                Thread.sleep(Config.RECOVER_TIME);
//            } catch (InterruptedException e) {
//            }
//
//            if (this.isStop) {
//                return;
//            }
//        }

    }
}
