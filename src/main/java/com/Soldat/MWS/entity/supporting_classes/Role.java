package com.Soldat.MWS.entity.supporting_classes;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_USER, ROLE_ADMIN;


    @Override
    public String getAuthority() {
        return name();
    }
}
