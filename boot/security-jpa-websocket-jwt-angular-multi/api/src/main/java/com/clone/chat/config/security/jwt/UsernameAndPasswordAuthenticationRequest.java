package com.clone.chat.config.security.jwt;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UsernameAndPasswordAuthenticationRequest {

    private String username;
    private String password;
    private String type;

    /*
        type	string	email	email , kakao, google , naver
        id	string	1234	SNS 회원 ID, 이메일 비밀번호
        email	string	a@abc.com	이메일
        device	object
        id	string	112233	기기 고유값
        name	string	iphoneX	기기 이름
        push_key	string	22334455676	푸시키
        os_type	string	android	ios, android
        language	string	ko	언어 코드
     */
}
