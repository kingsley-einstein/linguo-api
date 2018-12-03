package api.linguo.auth.security;

import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.http.HttpMethod;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(ResourceServerSecurityConfigurer configurer) throws Exception {
        configurer
        .resourceId("linguo-2018-M21TILIN7ual")
        .stateless(false);
    }

    @Override
    public void configure(HttpSecurity security) throws Exception {
        security
        .csrf()
        .disable()
        .authorizeRequests()
        .antMatchers("/api/v1/users")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .exceptionHandling()
        .accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }
}
