package com.gl.eshopping.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
/**
 * @EnableWebSecurity annotation enables spring security configuration which is defined in WebSecurityConfigurerAdapter
 */
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


    /**
     *
     * @param http
     * We have extended WebSecurityConfigurerAdapter, which allows us to override spring’s security default feature. In our example we want all the requests to be authenticated using HTTP Basic authentication.
     * configure() method configures the HttpSecurity class which authorizes each HTTP request which has been made. In our example ‘/user’ should be allowed for the user with USER role  and ‘/admin’ should be allowed for the user with USER (or) ADMIN role.
     * .httpBasic() –> Tells spring to use the HTTP Basic Authentication method to authenticate the user
     * authorizeRequests() .antMatchers(“/user”).hasRole(“USER”) .antMatchers(“/admin”).hasRole(“ADMIN”) –> All requests to the endpoint must be authorized or else they should be rejected.
     * .csrf().disable() –> Disables CSRF protection
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.httpBasic().and().authorizeRequests()
                .antMatchers("/user","/user/customers","/user/customers/{customerId}/orders/{orderId}").hasRole("USER")
                .antMatchers("/admin","/admin/sellers","/admin/category","/admin/category/{categoryId}","/admin/customers","/admin/customers/{customerId}","/eshopping/admin/products").hasRole("ADMIN")
                .and()
                .csrf().disable();
    }


    /**
     * configureGlobal() method configures the AuthenticationManagerBuilder class with the valid user credentials and the allowed roles. The AuthenticationManagerBuilder class creates the AuthenticationManger which is responsible for authenticating the credentials. In our example we have used the inMemoryAuthentication, you can choose other authentication types such JDBC, LDAP.
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception
    {
        auth.inMemoryAuthentication()
                .withUser("user").password(passwordEncoder().encode("user")).roles("USER")
                .and()
                .withUser("admin").password(passwordEncoder().encode("admin")).roles("USER", "ADMIN");
    }


    /**
     * In Spring Boot 2, we need to pass the encoded password to the password() method, since we have used BCryptPasswordEncoder we are directly encoding the password using encode() method.
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
