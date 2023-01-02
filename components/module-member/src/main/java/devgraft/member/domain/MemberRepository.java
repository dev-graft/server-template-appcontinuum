package devgraft.member.domain;

import org.springframework.data.repository.Repository;

public interface MemberRepository extends Repository<Member, Long> {
    <S extends Member> S save(S entity);
}
