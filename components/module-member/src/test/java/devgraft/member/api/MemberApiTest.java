package devgraft.member.api;

import devgraft.member.app.SignUpUseCase;
import devgraft.member.app.SignUpUseCase.SignUpRequest;
import devgraft.member.app.SignUpUseCase.SignUpResponse;
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

    @DisplayName("회원가입 요청이 성공했을 경우 HttpStatus.CREATED를 반환한다.")
    @Test
    void signUp_CreatedStatus() throws Exception {
        mockMvc.perform(post("/members/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @DisplayName("회원가입 요청문은 Service에 전달되어야한다.")
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
    }

    @DisplayName("회원 가입 요청 결과는 SignUpUseCase 결과와 같다.")
    @Test
    void signUp_returnValue() throws Exception {
        final String givenIdentityCode = "uniqId";
        BDDMockito.given(signUpUseCase.signUp(any())).willReturn(new SignUpResponse(givenIdentityCode));

        mockMvc.perform(post("/members/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.identityCode", equalTo(givenIdentityCode)));
    }
}