package com.ian.springcore.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service // @Service 내부에 @Component 애너테이션이 있기 때문에 컴포넌트 스캔으로 스프링 빈으로 자동 등록
@RequiredArgsConstructor // @RequiredArgsConstructor: final 필드의 생성자 주입 코드를 대신 작성해줌
public class MemberServiceImpl implements MemberService {

    // config를 통한 생성자 주입 방식으로 변경
    //    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /**
     * 의존성 주입 방식: 생성자 주입
     * - 생성자 호출 시점에 딱 1번만 호출되는 것이 보장됨
     * - 불변, 필수 의존 관계에 주로 사용
     * 1. 사용할 필드 선언
     * 2. 생성자 호출로 필드 값 초기화 및 객체 생성
     * 3. 필드가 final이기 때문에 한 번 초기화 된 값을 변경할 수 없음
     */
    private final MemberRepository memberRepository;

    // Autowired: 의존 관계를 자동으로 주입, 생성자가 하나일 경우 생략 가능
//    @Autowired // 기본적으로 타입이 동일한 빈을 찾아서 주입 -> ac.getBean(MemberRepository.class);
//    public MemberServiceImpl(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

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
