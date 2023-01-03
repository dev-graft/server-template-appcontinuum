package devgraft.credentials;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.security.Principal;

@AllArgsConstructor
@Getter
public class PrincipalImpl implements Principal {
    private final String name;
}
