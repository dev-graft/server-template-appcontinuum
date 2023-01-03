package devgraft.friend.api;

import devgraft.credentials.Credentials;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequestMapping("friends")
@RequiredArgsConstructor
@RestController
public class FriendApi {
    // 친구 요청
    @PostMapping("posts")
    public void postFriend(@Credentials(granted={"ADMIN", "MEMBER"}, required = false) Principal principal, @RequestParam(name = "target") String targetIdentityCode) {
        // 인증이 있으면 좋고 없으면 말고

        // 인증 정보가 없는 상태의 요청 - 통과
        // 인증이 완료되었는데 요청하는 권한이 없을 때 - 권한 없음 에러메세지
        // 인증정보는 전달왔는데 인증 실패 - 너도 실패!

        return;
    }

    // 친구 요청 수락
    // 친구 요청 취소
    // 친구 요청 거절
    // 친구 관계 삭제
}
