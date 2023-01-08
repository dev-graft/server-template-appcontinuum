package devgraft.support.advice.reactive;

//@Configuration(proxyBeanMethods = false)
//@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
//@ConditionalOnClass(WebFluxConfigurer.class)
//@ConditionalOnMissingBean({ WebFluxConfigurationSupport.class })
//@AutoConfigureAfter({ ReactiveWebServerFactoryAutoConfiguration.class, CodecsAutoConfiguration.class,
//        ValidationAutoConfiguration.class })
//@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE + 10)
public class ReactiveConfig {
//    @Configuration(proxyBeanMethods = false)
//    @EnableConfigurationProperties({ WebFluxProperties.class })
//    @Import({ EnableWebFluxConfiguration.class })
//    public static class WebFluxConfig implements WebFluxConfigurer {
//        @Override
//        public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
//        }
//    }
//
//    @Configuration(proxyBeanMethods = false)
//    public static class EnableWebFluxConfiguration extends DelegatingWebFluxConfiguration {
//    }
//
//    @Bean
//    public ResponseBodyResultHandler responseBodyResultHandler(
//            ServerCodecConfigurer serverCodecConfigurer,
//            RequestedContentTypeResolver contentTypeResolver) {
//        return new ReactiveResponseAdvice(serverCodecConfigurer.getWriters(),
//                contentTypeResolver);
//    }
}