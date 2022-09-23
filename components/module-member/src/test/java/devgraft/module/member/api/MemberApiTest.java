package devgraft.module.member.api;

import devgraft.module.member.app.MembershipRequest;
import devgraft.module.member.app.MembershipService;
import devgraft.support.mapper.ObjectMapperTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MemberApiTest extends ObjectMapperTest {
    private MockMvc mockMvc;
    private MembershipService membershipService;

    @BeforeEach
    void setUp() {
        membershipService = mock(MembershipService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new MemberApi(membershipService))
                .build();
    }

    @DisplayName("회원가입 요청")
    @Test
    void membership() throws Exception {
        final MembershipRequest givenRequest = MembershipRequest.of(
                "loginId", "password", "nickname", "profileImage"
        );

        mockMvc.perform(post("/members/membership")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(getObjectMapper().writeValueAsString(givenRequest)))
                .andExpect(status().isCreated());

        verify(membershipService).membership(refEq(givenRequest));
    }
}