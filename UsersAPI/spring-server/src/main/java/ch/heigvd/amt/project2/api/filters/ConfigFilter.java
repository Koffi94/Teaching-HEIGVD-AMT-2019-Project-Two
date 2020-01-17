package ch.heigvd.amt.project2.api.filters;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigFilter {

    @Bean
    public FilterRegistrationBean<AuthenticationFilter> authenticationFilterRegistrationBean() {
        FilterRegistrationBean<AuthenticationFilter> registrationBean = new FilterRegistrationBean();
        AuthenticationFilter authenticationFilter = new AuthenticationFilter();
        registrationBean.setFilter(authenticationFilter);
        registrationBean.addUrlPatterns("/user/*");
        registrationBean.addUrlPatterns("/users");
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<AuthorizationFilter> authorizationFilterRegistrationBean() {
        FilterRegistrationBean<AuthorizationFilter> registrationBean = new FilterRegistrationBean();
        AuthorizationFilter authorizationFilter = new AuthorizationFilter();
        registrationBean.setFilter(authorizationFilter);
        registrationBean.addUrlPatterns("/users");
        registrationBean.setOrder(2);
        return registrationBean;
    }


}
