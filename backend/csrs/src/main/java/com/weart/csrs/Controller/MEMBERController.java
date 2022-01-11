package com.weart.csrs.Controller;

import com.weart.csrs.domain.MEMBER.MEMBER;
import com.weart.csrs.service.MEMBERService;
import com.weart.csrs.web.dto.MEMBERRequest;
import com.weart.csrs.web.dto.MemberResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ComponentScan({"com.weart.csrs.service.MEMBERService"})
@Controller
public class MEMBERController {

    @Autowired  //스프링컨테이너에있는 memberService 를 가져와 연결.
    public MEMBERController(MEMBERService memberService) {
        this.memberService = memberService;
    }



    private final MEMBERService memberService;
    protected Logger log = LoggerFactory.getLogger(this.getClass());

//    생성자로 연결

    //    회원가입 뷰
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("account", new MEMBER());
        return "/auth/registration";
    }
// Dto 로 바꿔야함.






    //    회원가입 폼
    @PostMapping("api/member/save")
    public String createMEMBER(Model model, MemberResponseDto memberResponseDto, BindingResult bindingResult) {
        try {
            List<MEMBER> memberExist = memberService.getByName(memberResponseDto.getName());
            if (memberExist != null) {
                bindingResult.rejectValue("name", "error.member", "기존에 있는 사용자 입니다");
            } else {
//                Service 회원가입
                memberService.createMember(memberResponseDto);
                model.addAttribute("member", new MEMBER());
                model.addAttribute("successMessage", "회원가입 성공");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("successMessage", "FAIL" + e.getMessage());
        }
        return "auth/registration";
    }

    //홈페이지
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<MEMBER> member = null;
        try{
            member = memberService.getByName(auth.getName());
        }catch ( Exception e){
            log.error(e.getMessage());
        }
        model.addAttribute("username", memberService.getByName(auth.getName()));
        model.addAttribute("adminMessage", "Content Available Only for Users with Admin Role");

        return "/index";
    }



    @GetMapping("api/arts/list")
    public List<MEMBER> selectAllMember() {
        return memberService.selectAll();
    }

    @GetMapping("api/arts/{id}")
    public MemberResponseDto selectMember(@PathVariable Long memberId) {
        return memberService.selectMemberById(memberId);
    }

    //MEMBER update
    @PutMapping("api/member/{id}")
    public Map<String, Object> update(@PathVariable("id") Long id, @RequestBody final MEMBERRequest member) {
        Map<String, Object> response = new HashMap<>();

        Long res = memberService.update(id, member);
        if(res > 0) {
            response.put("result", "SUCCESS");
        } else {
            response.put("result", "FAIL");
            response.put("reason", "일치하는 회원 정보가 없습니다. 사용자 id를 확인해주세요.");
        }

        return response;
    }

    @DeleteMapping("api/member/{id}")
    public Long deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
        return memberId;
    }

}
