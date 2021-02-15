package com.clone.chat.model;

import com.clone.chat.model.view.json.JsonViewApi;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserToken extends ModelBase {

    @JsonView({JsonViewApi.class})
    public String id;

    @JsonView({JsonViewApi.class})
    private Set<SimpleGrantedAuthority> authorities;

    @JsonView({JsonViewApi.class})
    public String token;

    @JsonView({JsonViewApi.class})
    public String tokenHeader;

    @Builder
    public UserToken(String id, Set<SimpleGrantedAuthority> authorities, String token, String tokenHeader) {
        this.id = id;
        this.authorities = authorities;
        this.token = token;
        this.tokenHeader = tokenHeader;
    }
}
