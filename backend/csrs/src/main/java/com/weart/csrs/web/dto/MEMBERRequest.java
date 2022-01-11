package com.weart.csrs.web.dto;

import com.weart.csrs.domain.MEMBER.MEMBER;
import com.weart.csrs.service.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MEMBERRequest {
    private Long id;
    private String name;
    private String email;
    private Role role;
    private String password;

    //MEMBER 객체로 변환
    public MEMBER toEntity(){
        return MEMBER.builder()
                .id(id)
                .name(name)
                .email(email)
                .role(role)
                .password(password)
                .build();
    }

    @Builder
    public MEMBERRequest(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

}
