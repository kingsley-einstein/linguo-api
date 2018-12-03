package api.linguo.auth.security;

import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenStore tokenStore;

    @Value("${linguo.admin}")
    private String client;

    @Value("${linguo.password}")
    private String secret;

    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
        configurer
        .inMemory()
        .withClient(client)
        .secret(encoder.encode(secret))
        .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit", "client_credentials")
        .scopes("read", "write", "trust")
        .accessTokenValiditySeconds(9 * 60 * 90)
        .refreshTokenValiditySeconds(9 * 90 * 90)
        .resourceIds("linguo-2018-M21TILIN7ual");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer configurer) throws Exception {
        configurer
        .tokenStore(tokenStore)
        .authenticationManager(manager);
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setTokenStore(tokenStore);

        return tokenServices;
    }
}