package com.weart.csrs.service;

import com.nimbusds.oauth2.sdk.util.StringUtils;
import com.weart.csrs.Repository.MEMBERRepository;
import com.weart.csrs.domain.MEMBER.MEMBER;
import com.weart.csrs.web.dto.MEMBERRequest;
import com.weart.csrs.web.dto.MemberResponseDto;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Transactional
@Getter
@Service
public class MEMBERService {

    private static final String NOT_FOUND_MEMBER_MESSAGE = "해당 유저를 찾을 수 없습니다.";

    private final MEMBERRepository memberRepository;

    public List<MEMBER> getByName(String name) throws Exception {
        return memberRepository.findByName(name);
    }
    public Optional<MEMBER> getByEmail(String email) throws Exception {
        return memberRepository.findByEmail(email);
    }

    @Autowired
    public MEMBERService(MEMBERRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //LOGIN
    @RequestMapping(value="/login.do",method= RequestMethod.POST)
    public String loginMember(MEMBER member){
        System.out.println("member : " + member);
        return "main";
    }


    //CRETE   DTO에서 파일 받아서 DB에 저장하기.
    @Transactional
    public Long createMember(MemberResponseDto memberResponseDto) {
//        if(memberRepository.findByEmail(member.getEmail()).isPresent()) {
//            return ;
//        }
        MEMBER insertedMember = memberRepository.save(MEMBER.builder()
                .name(memberResponseDto.getName())
                .email(memberResponseDto.getEmail())
                .password(memberResponseDto.getPassword())
                .role(Role.USER)
                .build());
        return insertedMember.getId();
    }

    //전체 조회기능
    @Transactional
    public List<MEMBER> selectAll(){
        return memberRepository.findAll();
/*        return memberRepository.findAll().stream()
                .map(MemberResponseDto::new)
                .collect(Collectors.toList());*/
    }
    @Transactional
    public MemberResponseDto selectMemberById(Long id) {
        MEMBER memberResponseDto = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(NOT_FOUND_MEMBER_MESSAGE));
        return new MemberResponseDto(memberResponseDto);
    }


    //UPDATE
    @Transactional
    public Long update(Long id, MEMBERRequest memberRequest) {
        //조회시 없으면 에러 표시
        MEMBER member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_MEMBER_MESSAGE));

        // 공백조사사
        if(StringUtils.isNotBlank(memberRequest.getEmail())) member.setEmail(memberRequest.getEmail());
        if(StringUtils.isNotBlank(memberRequest.getName())) member.setName(memberRequest.getName());
        if(StringUtils.isNotBlank(memberRequest.getPassword())) member.setPassword(memberRequest.getPassword());

        member.update(memberRequest);
        return id;
    }


    //삭제 기능
    @Transactional
    public void deleteMember(Long memberId) {
        MEMBER member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException(NOT_FOUND_MEMBER_MESSAGE));
        memberRepository.delete(member);
    }


//    public void createMember(MEMBER member) {
//        member.setEmail(member.getEmail());
//        member.setName(member.getName());
//        member.setRole(Role.valueOf("USER"));
//        member.setPassword(member.getPassword());
//        memberRepository.save(member);
//    }

}
