package test.learnspring.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import test.learnspring.domain.Member;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("Popo");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void FindByName() {
        Member member1 = new Member();
        member1.setName("Koko");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Yoyo");
        repository.save(member2);

        Member result = repository.findByName("Koko").get();

        assertThat(member1).isEqualTo(result);
    }

    @Test
    public void FindAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
