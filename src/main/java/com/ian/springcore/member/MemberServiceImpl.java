package com.ian.springcore.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // @Service 내부에 @Component 애너테이션이 있기 때문에 컴포넌트 스캔으로 스프링 빈으로 자동 등록
public class MemberServiceImpl implements MemberService {

    // config를 통한 생성자 주입 방식으로 변경
    //    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    // Autowired: 의존 관계를 자동으로 주입 -> 기본적으로 타입이 동일한 빈을 찾아서 주입
    @Autowired // ac.getBean(MemberRepository.class);
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long id) {
        return memberRepository.findById(id);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
