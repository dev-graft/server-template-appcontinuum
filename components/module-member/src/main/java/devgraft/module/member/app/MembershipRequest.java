package devgraft.module.member.app;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MembershipRequest {
    private String loginId;
    private String password;
    private String nickname;
    private String profileImage;

    private MembershipRequest(final String loginId, final String password, final String nickname, final String profileImage) {
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
        this.profileImage = profileImage;
    }

    public static MembershipRequest of(final String loginId, final String password, final String nickname, final String profileImage) {
        return new MembershipRequest(loginId, password, nickname, profileImage);
    }
}
