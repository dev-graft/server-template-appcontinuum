package devgraft.member.domain;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@NoArgsConstructor
@Component
public class IdentityCodeProvider {
    public String generatedIdentityCode() {
        return UUID.randomUUID().toString();
    }
}
