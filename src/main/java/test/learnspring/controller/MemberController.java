package test.learnspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import test.learnspring.domain.Member;
import test.learnspring.service.MemberService;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;
    //@Autowired private MemberService memberService; //필드주입 방법
    
    /*private MemberService memberService;
    
    @Autowired //setter주입 방법 (단점)호출할때 public으로 노출이 되어 있음
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }*/
    
    @Autowired //memberService를 스프링이 컨테이너의 memberService를 가져와 '연결'시켜줌
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    
    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        
        System.out.println("이름 : " + member.getName());
        
        memberService.join(member);
        
        return "redirect:/";
    }
    
    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
