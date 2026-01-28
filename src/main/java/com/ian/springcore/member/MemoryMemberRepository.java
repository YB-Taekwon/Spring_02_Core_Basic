package com.ian.springcore.member;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository // @Repository 내부에 @Component 애너테이션이 있기 때문에 컴포넌트 스캔으로 스프링 빈으로 자동 등록
public class MemoryMemberRepository implements MemberRepository {

    // 실무에서는 동시성 이슈 제어를 위해 ConcurrentHashMap 사용
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long id) {
        return store.get(id);
    }
}
