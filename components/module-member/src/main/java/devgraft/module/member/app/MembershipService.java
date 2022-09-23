package devgraft.module.member.app;

import devgraft.support.exception.ValidationError;
import devgraft.support.exception.ValidationException;
import org.springframework.stereotype.Service;

import java.util.List;

import static devgraft.module.member.domain.MemberConstant.MEMBERSHIP_FAILURE;

@Service
public class MembershipService {
    private final MembershipRequestValidator membershipRequestValidator;

    public MembershipService(final MembershipRequestValidator membershipRequestValidator) {
        this.membershipRequestValidator = membershipRequestValidator;
    }

    public Object membership(final MembershipRequest request) {
        final List<ValidationError> errors = membershipRequestValidator.validate(request);
        if (!errors.isEmpty()) throw new ValidationException(errors, MEMBERSHIP_FAILURE);
        // 아이디 중복 검증
        return null;
    }
}
