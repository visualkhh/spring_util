package com.company.service.monitor.domain.monitor;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity @NoArgsConstructor
@Table(name = "T_USER_COUNT")
public class UserCount {

    @Builder
    public UserCount(Integer seq, String serviceName, Integer count) {
        this.seq = seq;
        this.serviceName = serviceName;
        this.count = count;
    }

    @Id
    @Column(name = "SEQ")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer seq;

    @Column(name = "SERVICE_NAME")
    private String serviceName;

    @Column(name = "COUNT")
    private Integer count;

}
