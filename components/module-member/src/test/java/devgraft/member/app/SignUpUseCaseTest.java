package devgraft.member.app;

import devgraft.member.app.SignUpUseCase.SignUpRequest;
import devgraft.member.app.SignUpUseCase.SignUpResponse;
import devgraft.member.domain.AuthIssueService;
import devgraft.member.domain.AuthIssueService.AuthIssuedResponse;
import devgraft.member.domain.IdentityCodeProvider;
import devgraft.member.domain.Member;
import devgraft.member.domain.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

class SignUpUseCaseTest {
    private SignUpUseCase signUpUseCase;
    private IdentityCodeProvider identityCodeProvider;
    private MemberRepository memberRepository;
    private AuthIssueService authIssueService;

    @BeforeEach
    void setUp() {
        identityCodeProvider = Mockito.mock(IdentityCodeProvider.class);
        memberRepository = Mockito.mock(MemberRepository.class);
        authIssueService = Mockito.mock(AuthIssueService.class);

        signUpUseCase = new SignUpUseCase(identityCodeProvider, memberRepository, authIssueService);
    }

    @DisplayName("[회원가입] 요청문을 기반으로 회원 정보를 추가합니다.")
    @Test
    void signUp_saveMemberToRepository() {
        UUID givenUUID = UUID.randomUUID();
        final SignUpRequest givenRequest = new SignUpRequest("nickname");
        BDDMockito.given(identityCodeProvider.generatedIdentityCode()).willReturn(givenUUID.toString());
        BDDMockito.given(authIssueService.issued(any())).willReturn(AuthIssuedResponse.builder().build());

        signUpUseCase.signUp(givenRequest);

        Mockito.verify(identityCodeProvider, times(1)).generatedIdentityCode();
        ArgumentCaptor<Member> requestArgumentCaptor = ArgumentCaptor.forClass(Member.class);
        Mockito.verify(memberRepository, times(1)).save(requestArgumentCaptor.capture());
        Assertions.assertThat(requestArgumentCaptor.getValue().getIdentityCode()).isEqualTo(givenUUID.toString());
        Assertions.assertThat(requestArgumentCaptor.getValue().getNickname()).isEqualTo(givenRequest.getNickname());
    }

    @DisplayName("[회원가입] 생성된 회원정보의 ID Code를 인증 정보 발급 서비스에 전달합니다.")
    @Test
    void signUp_passesIdentityCodeToAuthIssueService() {
        UUID givenUUID = UUID.randomUUID();
        final SignUpRequest givenRequest = new SignUpRequest("nickname");
        BDDMockito.given(identityCodeProvider.generatedIdentityCode()).willReturn(givenUUID.toString());
        BDDMockito.given(authIssueService.issued(any())).willReturn(AuthIssuedResponse.builder().build());

        signUpUseCase.signUp(givenRequest);

        Mockito.verify(authIssueService, times(1)).issued(givenUUID.toString());
    }

    @DisplayName("[회원가입] 요청 결과는 서비스 접근을 위한 토큰을 반환합니다.")
    @Test
    void signUp_expectReturnValue() {
        UUID givenUUID = UUID.randomUUID();
        final SignUpRequest givenRequest = new SignUpRequest("nickname");
        final String givenAccessToken = "ACAC";
        final String givenRefreshToken = "RERE";

        BDDMockito.given(identityCodeProvider.generatedIdentityCode()).willReturn(givenUUID.toString());
        BDDMockito.given(authIssueService.issued(any())).willReturn(AuthIssuedResponse.builder()
                .accessToken(givenAccessToken)
                .refreshToken(givenRefreshToken)
                .build());

        final SignUpResponse result = signUpUseCase.signUp(givenRequest);

        Assertions.assertThat(result.getAccessToken()).isEqualTo(givenAccessToken);
        Assertions.assertThat(result.getRefreshToken()).isEqualTo(givenRefreshToken);
    }
}