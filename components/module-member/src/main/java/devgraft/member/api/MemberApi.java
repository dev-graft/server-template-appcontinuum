package devgraft.member.api;

import devgraft.member.app.SignUpUseCase;
import devgraft.member.app.SignUpUseCase.SignUpRequest;
import devgraft.member.app.SignUpUseCase.SignUpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("members")
@RequiredArgsConstructor
@RestController
public class MemberApi {
    private final SignUpUseCase signUpUseCase;

    @GetMapping
    public String home() {
        return "MY MEMBER-MICRO-SERVICE";
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("sign-up")
    public void signUp(@RequestBody final SignUpRequest request, HttpServletResponse httpServletResponse) {
        final SignUpResponse signUpResponse = signUpUseCase.signUp(request);

        httpServletResponse.setHeader("ACCESS-TOKEN", signUpResponse.getAccessToken());
        final Cookie cookie = new Cookie("REFRESH-TOKEN", signUpResponse.getRefreshToken());
        cookie.setHttpOnly(true);
        httpServletResponse.addCookie(cookie);
    }
}
