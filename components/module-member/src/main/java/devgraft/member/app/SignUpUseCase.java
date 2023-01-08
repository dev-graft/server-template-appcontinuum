package devgraft.member.app;

import devgraft.member.domain.AuthIssueService;
import devgraft.member.domain.AuthIssueService.AuthIssuedResponse;
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
    private final AuthIssueService authIssueService;
    @Transactional
    public SignUpResponse signUp(final SignUpRequest request) {
        final Member member = Member.create(request.getNickname(), identityCodeProvider, memberRepository);
        final AuthIssuedResponse issued = authIssueService.issued(member.getIdentityCode());
        return new SignUpResponse(issued.getAccessToken(), issued.getRefreshToken());
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
        private String accessToken;
        private String refreshToken;
    }
}
