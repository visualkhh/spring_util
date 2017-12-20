package com.visualkhh.api.model;

import lombok.*;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class DataVersion {
    private String dataType;
    private ZonedDateTime updateDt;
}
