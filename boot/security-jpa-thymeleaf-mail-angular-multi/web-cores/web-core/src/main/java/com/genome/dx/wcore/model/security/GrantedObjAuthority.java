package com.genome.dx.wcore.model.security;

import com.fasterxml.jackson.annotation.JsonView;
//import com.omnicns.medicine.model.view.json.JsonViewFrontEnd;

public class GrantedObjAuthority<T>  extends com.omnicns.web.spring.security.GrantedObjAuthority<T>  {
    public GrantedObjAuthority(String role) {
        super(role);
    }

    public GrantedObjAuthority(String role, T roleAuth) {
        super(role, roleAuth);
    }

//    @JsonView({JsonViewFrontEnd.class})
    @Override
    public String getAuthority() {
        return super.getAuthority();
    }

//    @JsonView({JsonViewFrontEnd.class})
    @Override
    public T getAuth() {
        return super.getAuth();
    }
}
