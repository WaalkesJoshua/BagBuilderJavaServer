package com.Bagbuilder.RestAPI.Security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    private final String apiKey;

    public FilterConfig(@Value("${app.apikey}") String apiKey) {
        this.apiKey = apiKey;
    }

    @Bean
    public FilterRegistrationBean<AuthFilter> myPreFilter() {
        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthFilter(apiKey));
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
