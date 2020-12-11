package com.company.service.monitor.model.elasticsearch;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Shards {
    Long total;
    Long successful;
    Long skipped;
    Long failed;
}

