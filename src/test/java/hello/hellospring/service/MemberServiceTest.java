package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

  MemberService memberService;
  MemoryMemberRepository repository;

  @BeforeEach
  public void beforeEach() {
    repository = new MemoryMemberRepository();
    memberService = new MemberService(repository);
  }

  @AfterEach
  public void afterEach() {
    repository.clearStore();
  }

  @Test
  void join() {
    Member member = new Member();
    member.setName("hello");

    Long saveId = memberService.join(member);

    Member findMember = memberService.findOne(saveId).get();
    Assertions.assertThat(findMember.getName()).isEqualTo(member.getName());
    Assertions.assertThat(findMember).isEqualTo(member);
  }

  @Test
  void joinException() {
    Member member1 = new Member();
    member1.setName("hello");

    Member member2 = new Member();
    member2.setName("hello");

    memberService.join(member1);
    org.junit.jupiter.api.Assertions.assertThrows(
        IllegalStateException.class, () -> memberService.join(member2));
  }

  @Test
  void findMembers() {
  }

  @Test
  void findOne() {
  }
}
