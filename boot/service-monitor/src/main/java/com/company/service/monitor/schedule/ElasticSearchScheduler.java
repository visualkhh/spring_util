package com.company.service.monitor.schedule;

import com.company.service.monitor.model.elasticsearch.MindCare;
import com.company.service.monitor.model.elasticsearch.Omnifit2;
import com.company.service.monitor.services.MailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ElasticSearchScheduler {

    @Autowired
    MailService javaMailSender;

    @Qualifier("elasticsearchClient")
    @Autowired
    RestHighLevelClient restHighLevelClient;

    @Autowired
    SpringTemplateEngine springTemplateEngine;

    @Value("${projects.properties.email-serviceteam}")
    String emailServiceteam;

    Date omnifit2Last = new Date();
    @Scheduled(cron = "*/5 * * * * *")
    public void omnifit2Monitor() throws Throwable {
        log.info("omnifit2Monitor start");
        SearchRequest searchRequest = new SearchRequest("omnifit2");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(100);
        searchSourceBuilder.sort(new FieldSortBuilder("@timestamp").order(SortOrder.DESC));


        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        BoolQueryBuilder shouldQueryBuilder = QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("log_level", "ERROR"));
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        boolQueryBuilder.filter(shouldQueryBuilder).filter(QueryBuilders.rangeQuery("@timestamp").gt(sdf.format(omnifit2Last)).timeZone("Asia/Seoul"));

        searchSourceBuilder.query(boolQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHits = hits.getHits();
        List<Omnifit2> datas = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        for (int i = 0; i < searchHits.length; i++) {
            SearchHit hit = searchHits[i];
            String index = hit.getIndex();
            String id = hit.getId();
            float score = hit.getScore();
            String sourceAsString = hit.getSourceAsString();
            Omnifit2 care = objectMapper.readValue(sourceAsString, Omnifit2.class);
            care.set_id(id);
            care.set_index(hit.getIndex());
            care.set_type(hit.getType());


            if (omnifit2Last.getTime() < care.getTimestamp().getTime()) {
                datas.add(care);
            }
        }


        datas.forEach(it -> {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            log.debug(it.get_id() + "{} {}-> {}", format.format(it.getTimestamp()), it.getException_class(), it);
        });

        datas = datas.stream()
                .filter(it -> !"/api/user".equals(it.getUrl_path()) && null!=it.getMsg() && !it.getMsg().contains("serialNo"))
                .filter(it -> null!=it.getMsg() && !it.getMsg().contains("Could not parse 'Accept' header"))
                .filter(it -> null!=it.getMsg() && !it.getMsg().contains("Could not find acceptable representation"))
                .collect(Collectors.toList());

        if (datas.size() > 0) {
            omnifit2Last = datas.get(0).getTimestamp();
        }
        log.info("==========Monitor size:{}", datas.size());

        if (datas.size() > 0) {
            String title = String.format("%s %s", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()), "omnifit2 5초간격 ERROR 발생 (" + datas.size() + "건)");
            Context context = new Context();
            context.setVariable("logs", datas);
            String html = springTemplateEngine.process("emails/log-error", context);
            javaMailSender.sendHtml(emailServiceteam, title, html);
        }
    }


    Date mindcareLast = new Date();
    @Scheduled(cron = "*/5 * * * * *")
    public void mindcareMonitor() throws Throwable {
        log.info("mindcareMonitor start");
        SearchRequest searchRequest = new SearchRequest("mindcare*");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(100);
        searchSourceBuilder.sort(new FieldSortBuilder("@timestamp").order(SortOrder.DESC));

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        BoolQueryBuilder shouldQueryBuilder = QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("log_level", "ERROR"));
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        boolQueryBuilder.filter(shouldQueryBuilder).filter(QueryBuilders.rangeQuery("@timestamp").gt(sdf.format(mindcareLast)).timeZone("Asia/Seoul"));

        searchSourceBuilder.query(boolQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHits = hits.getHits();
        List<MindCare> datas = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();


        for (int i = 0; i < searchHits.length; i++) {
            SearchHit hit = searchHits[i];
            String index = hit.getIndex();
            String id = hit.getId();
            float score = hit.getScore();
            String sourceAsString = hit.getSourceAsString();
            MindCare care = objectMapper.readValue(sourceAsString, MindCare.class);
            care.set_id(id);
            care.set_index(hit.getIndex());
            care.set_type(hit.getType());


            if (mindcareLast.getTime() < care.getTimestamp().getTime()) {
                datas.add(care);
            }
        }
        datas.forEach(it -> {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            log.debug(it.get_id() + "{} {}-> {}", format.format(it.getTimestamp()), it.getException_class(), it);
        });

        datas = datas.stream()
                .filter(it -> !"org.apache.catalina.connector.ClientAbortException".equals(it.getException_class()))
                .filter(it-> !"org.springframework.security.access.AccessDeniedException".equals((it.getException_class())))
                .filter(it-> null!=it.getMessage() && !it.getMessage().contains("java.lang.String cannot be cast to com.ko.company.omnifit.login.vo.LoginVO"))
                .filter(it-> null!=it.getMsg() && !it.getMsg().contains("bypass"))
                .filter(it-> !"M1001".equals(it.getCode()) && !"/api/AI201".equals(it.getUrl_path()))
                .filter(it-> !"M1013".equals(it.getCode()) && !"/api/AI025".equals(it.getUrl_path())) //개인화엡 validation 오류
                .filter(it -> !"c.k.o.o.s.s.CustomAuthenticationProvider".equals(it.getJava_class()))
                .collect(Collectors.toList());

        if (datas.size() > 0) {
            mindcareLast = datas.get(0).getTimestamp();
        }
        log.info("==========>Monitor size:{}", datas.size());
        if (datas.size() > 0) {
            String title = String.format("%s %s", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()), "mindcare 5초간격 ERROR 발생 (" + datas.size() + "건)");
            Context context = new Context();
            context.setVariable("logs", datas);
            String html = springTemplateEngine.process("emails/log-error", context);
            javaMailSender.sendHtml(emailServiceteam, title, html);
        }
    }
}
