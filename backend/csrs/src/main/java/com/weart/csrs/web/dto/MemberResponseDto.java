package com.weart.csrs.web.dto;

import com.weart.csrs.domain.MEMBER.MEMBER;
import com.weart.csrs.service.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberResponseDto {
    private Long id;
    private String name;
    private String email;
    private Role role;
    private String password;

    public MemberResponseDto(MEMBER member) {
        this.id = member.getId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.role = member.getRole();
        this.password = member.getPassword();
    }

}


