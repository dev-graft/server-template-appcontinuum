package devgraft.member.domain;

import lombok.Builder;
import lombok.Getter;

public interface AuthIssueService {
    AuthIssuedResponse issued(final String identityCode);

    @Builder
    @Getter
    class AuthIssuedResponse {
        private String accessToken;
        private String refreshToken;
    }
}
