package com.hzy.spring.springwebflux.config;

import com.hzy.spring.springwebflux.message.CustomMessageReader;
import com.hzy.spring.springwebflux.message.CustomMessageWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
public class CustomWebFluxConfig implements WebFluxConfigurer {


    /**
     * 全局跨域配置，根据各自需求定义
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("*")
                .exposedHeaders(HttpHeaders.SET_COOKIE);
    }

    @Override
    public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
        configurer.customCodecs().reader(new CustomMessageReader());
        configurer.customCodecs().writer(new CustomMessageWriter());

        //由于AutoConfigure会自动覆盖jackson2JsonEncoder/Decoder，此配置无法生效
        //configurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder());
        //configurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder());
    }

    /**
     * 也可以继承CorsWebFilter使用@Component注解，效果是一样的
     * @return
     */
    @Bean
    CorsWebFilter corsWebFilter(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addExposedHeader(HttpHeaders.SET_COOKIE);
        CorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        ((UrlBasedCorsConfigurationSource) corsConfigurationSource).registerCorsConfiguration("/**",corsConfiguration);
        return new CorsWebFilter(corsConfigurationSource);
    }


}
