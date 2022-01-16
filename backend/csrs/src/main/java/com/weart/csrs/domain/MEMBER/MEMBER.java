package com.weart.csrs.domain.MEMBER;

import com.weart.csrs.domain.BaseTimeEntity;
import com.weart.csrs.service.Role;
import com.weart.csrs.web.dto.MEMBERRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
@Setter
public class MEMBER extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private String password;

    @Builder
    public MEMBER(Long id, String name, String email, String picture, Role role, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    public void update(MEMBERRequest memberRequest) {
        this.name = memberRequest.getName();
        this.email = memberRequest.getEmail();
        this.role = memberRequest.getRole();
        this.password = memberRequest.getPassword();
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

}