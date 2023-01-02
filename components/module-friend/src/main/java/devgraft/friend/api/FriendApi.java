package devgraft.friend.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("friends")
@RequiredArgsConstructor
@RestController
public class FriendApi {
    // 친구 요청
    @PostMapping("posts")
    public void postFriend(@RequestParam(name = "target") String targetIdentityCode) {
    }
    // 친구 요청 수락
    // 친구 요청 취소
    // 친구 요청 거절
    // 친구 관계 삭제
}