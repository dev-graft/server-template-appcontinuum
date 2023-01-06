package devgraft.member.api;

import devgraft.member.app.SignUpUseCase;
import devgraft.member.app.SignUpUseCase.SignUpRequest;
import devgraft.member.app.SignUpUseCase.SignUpResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

class MemberApiTest {
    private MockMvc mockMvc;
    private SignUpUseCase signUpUseCase;

    @BeforeEach
    void setUp() {
        signUpUseCase = Mockito.mock(SignUpUseCase.class);
        MemberApi memberApi = new MemberApi(signUpUseCase);
        mockMvc = MockMvcBuilders.standaloneSetup(memberApi).build();
    }

    // 회원가입이라는 API를 테스트할 때 관점
    // 해당 경로의 특정 메소드를 갖고 있는 API가 존재하는가?
    // 어떤 케이스에 어떤 Status가 반환되는가?
    // 어떤 케이스에 어떤 값이 반환 되는가?

    // API 존재 여부, 호출 시 어떻게 동작해야하는지(어떤 결과를 내야하는지)
    // 요청문을 회원가입 서비스에 전달한다.

    // 전달 과정은 어떻게 깐깐히 검사가 가능할까?
    // 당초에 서비스에게 전달해야한다! 라는 조건이 나온 순간
    // 테스트를 진행하려면? 서비스와 메서드가 존재해야하고, 해당 메소드의 I/O가 정의되어야 테스트 코드를 작성할 수 있는 것이다.

    @DisplayName("[회원가입] 정상 결과 Status는 CREATED(201) 입니다.")
    @Test
    void signUp_expectCreatedStatus() throws Exception {
        mockMvc.perform(post("/members/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @DisplayName("[회원가입] 요청받은 정보를 기반으로 Service를 요청합니다.")
    @Test
    void signUp_passesRequestToService() throws Exception {
        mockMvc.perform(post("/members/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "nickname": "givenNickname"
                        }
                        """));

        ArgumentCaptor<SignUpRequest> requestArgumentCaptor = ArgumentCaptor.forClass(SignUpRequest.class);
        Mockito.verify(signUpUseCase).signUp(requestArgumentCaptor.capture());
        Assertions.assertThat(requestArgumentCaptor.getValue().getNickname()).isEqualTo("givenNickname");
    }

    @DisplayName("[회원가입] 성공 시 헤더에 ACCESS-TOKEN이 저장됩니다.")
    @Test
    void signUp_responseAccessTokenToHeader() throws Exception {
        final String givenIdentityCode = "uniqId";
        BDDMockito.given(signUpUseCase.signUp(any())).willReturn(new SignUpResponse(null, null));

        mockMvc.perform(post("/members/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.identityCode", equalTo(givenIdentityCode)));
    }

    @DisplayName("[회원가입] 성공 시 쿠키에 REFRESH-TOKEN이 저장됩니다.")
    @Test
    void signUp_responseRefreshTokenToCookie() throws Exception {
        final String givenIdentityCode = "uniqId";
        BDDMockito.given(signUpUseCase.signUp(any())).willReturn(new SignUpResponse(null, null));

        mockMvc.perform(post("/members/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.identityCode", equalTo(givenIdentityCode)));
    }
}