package com.weart.csrs.service;

import com.weart.csrs.Repository.MEMBERRepository;
import com.weart.csrs.domain.MEMBER.MEMBER;
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

    public void createMember(MEMBER member) {
        member.setEmail(member.getEmail());
        member.setName(member.getName());
        member.setRole(Role.valueOf("USER"));
        memberRepository.save(member);
    }


    //Email로 계정 하나만 생성 가능.
//    private void validateDuplicateMember(MEMBER member) {
//        memberRepository.findByEmail(member.getEmail())
//                .ifPresent(m -> {
//                    throw new IllegalStateException("이미 존재하는 회원입니다.");
//                });
//    }

    @RequestMapping(value="/login.do",method= RequestMethod.POST)
    public String loginMember(MEMBER member){
        System.out.println("member : " + member);
        return "main";
    }

//    @Transactional
//    public int update(long id, final MEMBERRequest u) {
//        Optional<MEMBER> oUser = MEMBERRepository.findById(id);
//        if(oUser.isPresent())
//            return 0;
//
//        MEMBER user = oUser.get();
//        user.setBirthDate(u.getBirthDate());
//        user.setEmail(u.getEmail());
//        user.setName(u.getName());
//        user.setPassword(u.getPassword());
//        user.setPhoneNumber(u.getPhoneNumber());
//        user.setSex(u.getSex());
//        user.setType(u.getType());
//        MEMBERRepository.save(user);
//        return 1;
//    }

}
