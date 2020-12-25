package com.ceragem.iot.api.code;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;


public enum UserRole {
    ADMIN,         //최고,최고관리자
    CORP,          //업체,업체관리자
    CORP_ADMIN,    //업체 관리자,업체 관리자
    DOCS,          //DOCS,DOCS
    NORMAL,        //노말
    SWAGGER,       //SWAGGER,SWAGGER

    AUTH,
    USER;

//    USER(Sets.newHashSet()),
//    ADMIN(Sets.newHashSet(COURSE_READ, COURSE_WRITE, STUDENT_READ, STUDENT_WRITE)),
//    ADMINTRAINEE(Sets.newHashSet(COURSE_READ, STUDENT_READ));

//    private Set<Object> permissions;
//
//    UserRole(Set<ApplicationUserPermission> permissions) {
//        this.permissions = permissions;
//    }
//
//    public Set<ApplicationUserPermission> getPermissions() {
//        return permissions;
//    }

    //    public Long getL() {
//            return 1l;
//    }
    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
//        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
//                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
//                .collect(Collectors.toSet());
        Set<SimpleGrantedAuthority> permissions = new HashSet<>();
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
