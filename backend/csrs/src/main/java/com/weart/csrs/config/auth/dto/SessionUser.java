package com.weart.csrs.config.auth.dto;

import com.weart.csrs.domain.MEMBER.MEMBER;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String password;

    public SessionUser(MEMBER member) {
        this.name = member.getName();
        this.email = member.getEmail();
        this.password = member.getPassword();
    }
}
