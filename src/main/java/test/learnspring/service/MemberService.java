package test.learnspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.learnspring.controller.MemberForm;
import test.learnspring.domain.Member;
import test.learnspring.repository.MemberRepository;
import test.learnspring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

//직접 스프링 빈 등록을 위해 주석 @Service// 이것이 Service일줄 알고 컨테이너에 등록함
public class MemberService {
    
    private final MemberRepository memberRepository;
    
    //직접 스프링 빈 등록을 위해 주석 @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;//memberRepository를 받아서 여기서 사용하는 memberRepository변수에 넣어 다른곳에서 사용해도 같은 DB를 사용하게 할 수 있음(한 객체를 사용해서)
    }
    
    
    /*회원가입*/
    public Long join(Member member) {
        //같은 이름이 있는 중복 회원은 안된다
        validateDuplicateMember(member);
        
        memberRepository.save(member);
        return member.getId();
    }
    
    private void validateDuplicateMember(Member member) { //회원이름 중복 확인
        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");//IllegalStateException에 "이미 존재하는 회원입니다."라는 메세지를 넣어주기
        });
    }
    
    /*전체 회원 조회*/
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
