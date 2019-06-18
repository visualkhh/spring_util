package com.lgu.cbc.cell;

import com.lgu.cbc.core.CbcApplicationBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.ApplicationPidFileWriter;

@SpringBootApplication
@Slf4j
public class CellApplication extends CbcApplicationBase {

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(CellApplication.class);
        builder.build().addListeners(new ApplicationPidFileWriter());
        builder.run(args);
        builder.context().getBean(CellApplication.class).start();
    }

    @Override
    public void loopRun() {
//        try {
//            Cell.makeMap();
//            Region1.makeHashtable();
//            vt = this.parseRawCell(Config.CELL_FILE_NAME);
//            this.backupCell();
//            this.saveCell(vt);
//            this.saveTempCellcode();
//            this.saveCellcode();
//            this.updateCellcode();
//            this.makeJson();
//            this.makeVersion();
//        } catch (FileNotFoundException e) {
//            TloLogger.w(Worker.reqTime, "localhost", "1001");
//            e.printStackTrace();
//        } catch (IOException e) {
//            TloLogger.w(Worker.reqTime, "localhost", "1000");
//            e.printStackTrace();
//        } catch (SQLException e) {
//            TloLogger.w(Worker.reqTime, "db", "2000");
//            e.printStackTrace();
//        } finally {
//            try {
//                this.updateRunDatetime();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//            logger.info("Process[Finish]");
//        }
    }
}
