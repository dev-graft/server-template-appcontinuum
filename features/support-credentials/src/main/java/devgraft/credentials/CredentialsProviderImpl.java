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
}
