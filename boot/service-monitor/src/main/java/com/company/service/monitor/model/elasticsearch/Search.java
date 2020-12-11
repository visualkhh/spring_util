package com.company.service.monitor.model.elasticsearch;

import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class Search {
    Long took;
    Boolean timed_out;

    Shards _shards;
    HitContainer hits;
}
