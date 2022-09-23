package devgraft.module.member.api;

import devgraft.module.member.app.MembershipRequest;
import devgraft.module.member.app.MembershipService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("members")
@RestController
public class MemberApi {
    private final MembershipService membershipService;

    public MemberApi(final MembershipService membershipService) {
        this.membershipService = membershipService;
    }

    // 회원가입
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("membership")
    public void membership(@RequestBody final MembershipRequest request) {
        membershipService.membership(request);
    }
    // 로그인
    // 회원조회
    // 회원 업데이트
    // 회원삭제
}
