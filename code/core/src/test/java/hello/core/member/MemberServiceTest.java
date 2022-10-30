package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join() {
        //given : 주어진 것
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when : ~했을 때
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then : 그 결과
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
