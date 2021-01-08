package test.learnspring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import test.learnspring.repository.MemberRepository;
import test.learnspring.repository.MemoryMemberRepository;
import test.learnspring.service.MemberService;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }
    
    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
