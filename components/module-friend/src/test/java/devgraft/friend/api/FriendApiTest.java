package devgraft.friend.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class FriendApiTest {
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(FriendApi.class).build();
    }

    @DisplayName("친구 요청이 성공했을 시 HttpStatus 결과는 OK다.")
    @Test
    void posFriend_expectOkHttpStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/friends/posts")
                        .principal(new Credentials("test"))
                        .param("target", "targetId"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}