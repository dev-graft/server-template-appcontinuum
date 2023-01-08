package devgraft.credentials;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

public class CredentialsProviderImpl implements CredentialsProvider {
    @Override
    public Principal generated(final MethodParameter parameter, final ModelAndViewContainer mavContainer, final NativeWebRequest webRequest, final WebDataBinderFactory binderFactory) {
        //
        final HttpServletRequest httpServletRequest = (HttpServletRequest) webRequest.getNativeRequest();

        // header, session, cookie

        // jwt

        // 검증

        // 정보

        return new PrincipalImpl("data");
    }

    // jwt 검증에 필요한건 받급에 사용된 secret 정보
    // jwt가 변경되던 뭐가되었든 결국 한 곳에서 인가된 토큰을 검증하고 관리해야한다는건 같다.
    // jwt라면 secret이 필요한데 이 중요한 값을 각자 관리되는 MS에 들고 있는 것은 무리고
    // 인증 방법이 각자 다르면 당연히 다른 곳에 토큰을 보내도 인증못하닌까 그것도 무리
    // 그게 아니라면 각 서비스가 인가된 토큰을 검증할줄 알아야기때문
}
