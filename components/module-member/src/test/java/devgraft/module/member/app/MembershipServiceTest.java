package devgraft.module.member.app;

import devgraft.support.exception.ValidationError;
import devgraft.support.exception.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class MembershipServiceTest {
    private MembershipService membershipService;
    private MembershipRequestValidator membershipRequestValidator;

    @BeforeEach
    void setUp() {
        membershipRequestValidator = mock(MembershipRequestValidator.class);
        membershipService = new MembershipService(membershipRequestValidator);
    }

    @DisplayName("회원가입 요청이 입력 조건과 맞지 않으면 에러")
    @Test
    void membershipRequestHasError() {
        MembershipRequest givenRequest = MembershipRequest.of("", "", "", "");
        given(membershipRequestValidator.validate(any())).willReturn(List.of(ValidationError.of("field", "message")));

        final ValidationException validationException = catchThrowableOfType(
                () -> membershipService.membership(givenRequest),
                ValidationException.class);

        assertThat(validationException).isNotNull();
        assertThat(validationException.getErrors()).isNotNull();
        assertThat(validationException.getErrors()).isNotEmpty();
        assertThat(validationException.getErrors().get(0).getField()).isEqualTo("field");
        assertThat(validationException.getErrors().get(0).getMessage()).isEqualTo("message");
    }
}