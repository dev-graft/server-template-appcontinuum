package devgraft.member.app;

import devgraft.member.app.SignUpUseCase.SignUpRequest;
import devgraft.member.app.SignUpUseCase.SignUpResponse;
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

import static org.mockito.Mockito.times;

class SignUpUseCaseTest {
    private SignUpUseCase signUpUseCase;
    private IdentityCodeProvider identityCodeProvider;
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        identityCodeProvider = Mockito.mock(IdentityCodeProvider.class);
        memberRepository = Mockito.mock(MemberRepository.class);

        signUpUseCase = new SignUpUseCase(identityCodeProvider, memberRepository);
    }

    @DisplayName("요청정보를 기반으로 회원정보를 생성 후 저장되어야한다.")
    @Test
    void signUp_saveMemberToRepository() {
        final SignUpRequest givenRequest = new SignUpRequest("nickname");
        UUID givenUUID = UUID.randomUUID();
        BDDMockito.given(identityCodeProvider.generatedIdentityCode()).willReturn(givenUUID.toString());

        signUpUseCase.signUp(givenRequest);

        Mockito.verify(identityCodeProvider, times(1)).generatedIdentityCode();

        ArgumentCaptor<Member> requestArgumentCaptor = ArgumentCaptor.forClass(Member.class);
        Mockito.verify(memberRepository, times(1)).save(requestArgumentCaptor.capture());
        final Member member = requestArgumentCaptor.getValue();
        Assertions.assertThat(member.getIdentityCode()).isEqualTo(givenUUID.toString());
        Assertions.assertThat(member.getNickname()).isEqualTo(givenRequest.getNickname());
    }

    @DisplayName("회원가입 요청 결과는 요구사항과 일치해야한다.")
    @Test
    void signUp_expectReturnValue() {
        final SignUpRequest givenRequest = new SignUpRequest("nickname");
        UUID givenUUID = UUID.randomUUID();
        BDDMockito.given(identityCodeProvider.generatedIdentityCode()).willReturn(givenUUID.toString());

        final SignUpResponse result = signUpUseCase.signUp(givenRequest);

        Assertions.assertThat(result.getIdentityCode()).isEqualTo(givenUUID.toString());
    }
}