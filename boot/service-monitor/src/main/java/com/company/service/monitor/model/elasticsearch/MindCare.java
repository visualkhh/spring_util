package com.company.service.monitor.model.elasticsearch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)//https://lng1982.tistory.com/180
@Document(indexName = "mindcare_care")
public class MindCare extends ElasticSearch{
    String log_level;
    String url_path;
    String service_name;
    String service_type;
    String stacktrace;
    String ip;
    String code;
    String java_method;
    String exception_class;
    String message;
    String java_line;
    String java_class;
    String path;
    String msg;
    String host;
    String date;

    String[] paths;

}
