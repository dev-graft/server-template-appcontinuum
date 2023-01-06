package devgraft.support.advice.reactive;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.core.MethodParameter;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.web.reactive.HandlerResult;
import org.springframework.web.reactive.accept.RequestedContentTypeResolver;
import org.springframework.web.reactive.result.method.annotation.ResponseBodyResultHandler;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.pattern.PathPattern;
import org.springframework.web.util.pattern.PathPatternParser;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
public class ReactiveResponseAdvice extends ResponseBodyResultHandler {
    private static final List<PathPattern> ignores = Arrays.asList(
            new PathPatternParser().parse("/v*/api-docs"),
            new PathPatternParser().parse("/docs/**"),
            new PathPatternParser().parse("/swagger-resources/**"),
            new PathPatternParser().parse("/swagger-ui.html"),
            new PathPatternParser().parse("/webjars/**"),
            new PathPatternParser().parse("/swagger/**"));
    private static MethodParameter param;

    static {
        try {
            param = new MethodParameter(ReactiveResponseAdvice.class
                    .getDeclaredMethod("methodForParams"), -1);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public ReactiveResponseAdvice(List<HttpMessageWriter<?>> writers, RequestedContentTypeResolver resolver) {
        super(writers, resolver);
    }

    @Override
    public boolean supports(HandlerResult result) {
        return true;
    }

    private static Object methodForParams() {
        return null;
    }

    @Override
    public Mono<Void> handleResult(ServerWebExchange exchange, HandlerResult result) {
//        if (ignores.stream().anyMatch(pathPattern -> pathPattern.matches(PathContainer.parsePath(exchange.getRequest().getURI().getPath()))))
//            return writeBody(result.getReturnValue(), param, exchange);
//        if (result.getReturnValue() instanceof CommonResult)  return writeBody(result.getReturnValue(), param, exchange);
        return writeBody(result.getReturnValue(), param, exchange);
//
//        if (result.getReturnValue() instanceof Mono) {
//            if (((Mono<?>) result.getReturnValue()).block() instanceof CommonResult) {
//                return writeBody(obj, param, exchange);
//            }
//        }
//        boolean isNotEmptyBody = obj != null;
//        CommonResult body = isNotEmptyBody ? SingleResult.success(obj) : CommonResult.success();
//        return writeBody(body, param, exchange);
    }
}