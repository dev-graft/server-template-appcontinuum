package devgraft.friend.api;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.security.Principal;

@AllArgsConstructor
@Getter
public class Credentials implements Principal {
    private final String name;
}
