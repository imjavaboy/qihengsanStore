package cqut.guobenqi.online_xdclass.config;

import cqut.guobenqi.online_xdclass.interceptor.CorsInterceptor;
import cqut.guobenqi.online_xdclass.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置
 * */
@Configuration
public class InterceptorConfig  implements WebMvcConfigurer {

    @Bean
    LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }

    @Bean
    CorsInterceptor corsInterceptor(){
        return new CorsInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**配置跨域*/
        registry.addInterceptor(corsInterceptor()).addPathPatterns("/**");

        registry.addInterceptor(loginInterceptor()).addPathPatterns("/api/v1/pri/*/*/**").excludePathPatterns("/api/v1/pri/user/login","/api/v1/pri/user/register");


//        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
