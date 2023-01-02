package devgraft.member.app;

import devgraft.member.domain.IdentityCodeProvider;
import devgraft.member.domain.Member;
import devgraft.member.domain.MemberRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SignUpUseCase {
    private final IdentityCodeProvider identityCodeProvider;
    private final MemberRepository memberRepository;

    @Transactional
    public SignUpResponse signUp(final SignUpRequest request) {
        final Member member = Member.create(request.getNickname(), identityCodeProvider, memberRepository);
        return new SignUpResponse(member.getIdentityCode());
    }

    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    public static class SignUpRequest {
        private String nickname;
    }

    @AllArgsConstructor
    @Getter
    public static class SignUpResponse {
        private String identityCode;
    }
}
