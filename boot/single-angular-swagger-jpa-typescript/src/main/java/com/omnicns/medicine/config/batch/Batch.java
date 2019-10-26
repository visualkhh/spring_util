package com.omnicns.medicine.config.batch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.IsoFields;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

@Component
@Slf4j @RequiredArgsConstructor @Configuration
public class Batch {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private JobLauncher jobLauncher;

//    @Scheduled(cron = "0 0 * * * *")
//    public void run() {
//        Date now = new Date();
//        SimpleDateFormat sDateformat = new SimpleDateFormat("yyyyMMdd");
//        // TODO: Timezone 되는지 봐야 함~!
//        sDateformat.setTimeZone(TimeZone.getTimeZone("GMT"));
//        this.runParam(sDateformat.format(now));
//    }


//    public void runParam(String now) {
//        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
//        jobParametersBuilder.addString("requestDate", now);
//
//        try {
//            jobLauncher.run(statisticsJob(), jobParametersBuilder.toJobParameters());
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    @Bean
//    public Job statisticsJob() {
//        Date now = new Date();
//        SimpleDateFormat sDateformat = new SimpleDateFormat("yyyyMMdd");
//
//        return jobBuilderFactory.get("statisticsJob")
//                .start(statisticsUserStep(sDateformat.format(now)))
//                .next(statisticsResultStep(sDateformat.format(now)))
//                .build();
//    }

//    /* User 통계 */
//    @Bean
//    @JobScope
//    public Step statisticsUserStep(@Value("#{jobParameters[requestDate]}") String requestDate) {
//        return stepBuilderFactory.get("statisticsUserStep")
//                .tasklet((contribution, chunkContext) -> {
//                    log.info("=====> statisticsUserStep run : " + requestDate);
//                    Object[][] userStatistics;
//                    String r_date = requestDate;
//
//                    if (null != r_date && !"".equals(r_date)) {
//                        userStatistics = userDvcRepository.getUserStatisticsParam(r_date);
//                        insertUserStatistics(userStatistics, getWeekMonth(r_date).get("weekly"), getWeekMonth(r_date).get("monthly"), ZonedDateTime.now());
//                    }/* else {
//                        r_date = getNowRDate();
//                        userStatistics = userDvcRepository.getUserStatisticsParam(r_date);
//                    }*/
////                    if (localDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)) weekly = weekly - 1;
//
//                    return RepeatStatus.FINISHED;
//                })
//                .build();
//    }


    private Map<String, Integer> getWeekMonth(String r_date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate localDate = LocalDate.parse(r_date, formatter);
        Map<String, Integer> weekMonth = new HashMap<>();
        weekMonth.put("weekly", localDate.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR));
        weekMonth.put("monthly", localDate.getMonthValue());
        return weekMonth;
    }

    private String getNowRDate() {
        Date now = new Date();
        SimpleDateFormat sDateformat = new SimpleDateFormat("yyyyMMdd");
        sDateformat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return sDateformat.format(now);
    }
}
