package test.learnspring.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.learnspring.domain.Member;
import test.learnspring.repository.MemoryMemberRepository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    
    MemberService memberService;
    MemoryMemberRepository memoryMemberRepository;
    
    @BeforeEach
    public void beforeEach() {//테스트를 실행하기 전 실행
        memoryMemberRepository = new MemoryMemberRepository();//memoryMemberRepository변수에 MemoryMemberRepository객체 넣기
        memberService = new MemberService(memoryMemberRepository);//memberService변수에 MemberService를 넣는데 여기서 사용하는m emoryMemberRepository변수를 넣어서 보냄
    }
    
    @AfterEach
    public void afterEach() {//각각의 한 테스트를 실행한 후마다 실행
        memoryMemberRepository.clearStore();//전에 저장된 값을 날려주는 함수 실행
    }
    
    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("Spring");
        
        //when
        Long saveId = memberService.join(member);//setName한 member를 memberService에 넣어 saveId변수에 저장
        
        //then
        Member findMember = memberService.findOne(saveId).get();//memberService에 아까 저장한 이름을 찾아 가져와 findMember변수에 저장
        assertThat(member.getName()).isEqualTo(findMember.getName());//member의 이름과 findMember의 이름이 일치한지
    }
    
    @Test
    public void 중복_회원_가입() {
        //given
        Member member1 = new Member();
        member1.setName("Spring");
        
        Member member2 = new Member();
        member2.setName("Spring");
        
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        //memberService의 join에 member2를 넣었을 때 IllegalStateException.class가 발생해야함
        
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");//IllegalStateException에 들어있는 메세지가 "이미 존재하는 회원입니다."과 같다면 통과
//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
        
        //then
    }
    
    @Test
    void 맴버찾기() {
    
    }
    
    @Test
    void 한명찾기() {
    
    }
}