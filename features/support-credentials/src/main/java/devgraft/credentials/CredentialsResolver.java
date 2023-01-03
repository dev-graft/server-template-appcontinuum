package devgraft.credentials;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.security.Principal;

public abstract class CredentialsResolver<T extends Principal> implements HandlerMethodArgumentResolver {
    private final Class<T> principalClass;
    private final CredentialsProvider<? extends Principal> credentialsProvider;

    protected CredentialsResolver(final Class<T> principalClass, final CredentialsProvider<T> credentialsProvider) {
        this.principalClass = principalClass;
        this.credentialsProvider = credentialsProvider;
    }

    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Credentials.class) &&
                principalClass.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(final MethodParameter parameter, final ModelAndViewContainer mavContainer, final NativeWebRequest webRequest, final WebDataBinderFactory binderFactory) throws Exception {
        return credentialsProvider.generated(parameter, mavContainer, webRequest, binderFactory);
    }
}
