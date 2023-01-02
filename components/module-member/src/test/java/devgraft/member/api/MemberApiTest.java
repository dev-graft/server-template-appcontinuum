package devgraft.member.api;

import devgraft.member.app.SignUpUseCase;
import devgraft.member.app.SignUpUseCase.SignUpResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
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
        mockMvc.perform(post("/members/sign-up"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @DisplayName("회원 가입 요청 결과는 SignUpUseCase 결과와 같다.")
    @Test
    void signUp_returnValue() throws Exception {
        final String givenUniqId = "uniqId";
        BDDMockito.given(signUpUseCase.signUp()).willReturn(new SignUpResponse(givenUniqId));

        mockMvc.perform(post("/members/sign-up"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.uniqId", equalTo(givenUniqId)));
    }
}