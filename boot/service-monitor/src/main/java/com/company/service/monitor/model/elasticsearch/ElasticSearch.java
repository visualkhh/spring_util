package com.company.service.monitor.model.elasticsearch;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ElasticSearch {
    @JsonProperty("@timestamp")
    Date timestamp; //: "2020-05-25T08:01:45.000Z",
    @JsonProperty("@version")
    Long version;//: "1",
    String _id;//: "1",
    String _index;
    String _type;
}
