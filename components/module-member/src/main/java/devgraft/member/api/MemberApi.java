package devgraft.member.api;

import devgraft.member.app.SignUpUseCase;
import devgraft.member.app.SignUpUseCase.SignUpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("members")
@RequiredArgsConstructor
@RestController
public class MemberApi {
    private final SignUpUseCase signUpUseCase;

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("sign-up")
    public SignUpResponse signUp() {
        return signUpUseCase.signUp();
    }
}
