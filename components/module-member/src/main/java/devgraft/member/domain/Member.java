package devgraft.member.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "MEMBER")
@Entity
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String identityCode;
    @Column(nullable = false)
    private String nickname;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    private Member(final Long id, final String identityCode, final String nickname) {
        this.id = id;
        this.identityCode = identityCode;
        this.nickname = nickname;
    }

    public static Member create(final String nickname, final IdentityCodeProvider provider, final MemberRepository repository) {
        final String identityCode = provider.generatedIdentityCode();
        final Member member = new Member(null, identityCode, nickname);
        repository.save(member);
        return member;
    }
}
