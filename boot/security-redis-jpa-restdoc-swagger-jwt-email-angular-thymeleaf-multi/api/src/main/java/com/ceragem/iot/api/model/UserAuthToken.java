package com.ceragem.iot.api.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class UserAuthToken {
    public String name;
    public String token;
    public String refreshToken;

    @Builder
    public UserAuthToken(String name, String token, String refreshToken) {
        this.name = name;
        this.token = token;
        this.refreshToken = refreshToken;
    }
}
