package devgraft.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

public interface AuthIssuedService {
    AuthIssuedResponse issue(AuthIssuedRequest request);

    @AllArgsConstructor
    @Getter
    class AuthIssuedRequest {

    }

    @AllArgsConstructor
    @Getter
    class AuthIssuedResponse {

    }
}
