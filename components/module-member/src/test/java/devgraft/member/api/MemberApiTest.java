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

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

class MemberApiTest {
    private MockMvc mockMvc;
    private SignUpUseCase signUpUseCase;

    @BeforeEach
    void setUp() {
        signUpUseCase = Mockito.mock(SignUpUseCase.class);
        final SignUpResponse givenSignUpResponse = new SignUpResponse(null, null);
        BDDMockito.given(signUpUseCase.signUp(any())).willReturn(givenSignUpResponse);

        MemberApi memberApi = new MemberApi(signUpUseCase);
        mockMvc = MockMvcBuilders.standaloneSetup(memberApi).build();
    }

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
        final SignUpResponse givenSignUpResponse = new SignUpResponse("ACC", null);
        BDDMockito.given(signUpUseCase.signUp(any())).willReturn(givenSignUpResponse);

        mockMvc.perform(post("/members/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.header().string("ACCESS-TOKEN", givenSignUpResponse.getAccessToken()));
    }

    @DisplayName("[회원가입] 성공 시 쿠키에 REFRESH-TOKEN이 저장됩니다.")
    @Test
    void signUp_responseRefreshTokenToCookie() throws Exception {
        final SignUpResponse givenSignUpResponse = new SignUpResponse(null, "REF");
        BDDMockito.given(signUpUseCase.signUp(any())).willReturn(givenSignUpResponse);

        mockMvc.perform(post("/members/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.cookie().value("REFRESH-TOKEN", givenSignUpResponse.getRefreshToken()))
                .andExpect(MockMvcResultMatchers.cookie().httpOnly("REFRESH-TOKEN", true));
    }
}