package xyz.hnnknk.deneb.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "my_rest_api";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID).stateless(false);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.
                anonymous().disable()
                .requestMatchers().antMatchers("/components/monitor/**")
                .requestMatchers().antMatchers("/components/ups/**")
                .requestMatchers().antMatchers("/components/mouse/**")
                .requestMatchers().antMatchers("/components/keyboard/**")
                .and().authorizeRequests()
                .antMatchers("/components/monitor/**").access("hasRole('ADMIN')")
                .antMatchers("/components/ups/**").access("hasRole('ADMIN')")
                .antMatchers("/components/mouse/**").access("hasRole('ADMIN')")
                .antMatchers("/components/keyboard/**").access("hasRole('ADMIN')")
                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }

}
