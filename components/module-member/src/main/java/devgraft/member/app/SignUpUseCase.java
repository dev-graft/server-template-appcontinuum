package devgraft.member.app;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
public class SignUpUseCase {

    public SignUpResponse signUp() {
        return null;
    }

    @AllArgsConstructor
    @Getter
    public static class SignUpResponse {
        private String uniqId;
    }
}
