package com.company.service.monitor.model.elasticsearch;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Hit {
    String _index;
    String _type;
    String _id;
    Double _score;
    MindCare _source;
    Sort _sort;
}
