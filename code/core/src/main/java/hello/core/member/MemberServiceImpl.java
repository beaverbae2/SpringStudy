package hello.core.member;

// 인터페이스에 대한 구현 클래스가 하나인 경우 이름을 '인테페이스명 + Impl' 으로 사용 가능
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
