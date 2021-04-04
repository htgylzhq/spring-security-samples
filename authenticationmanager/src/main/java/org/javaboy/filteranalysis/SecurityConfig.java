package org.javaboy.filteranalysis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
@Configuration
public class SecurityConfig {

    @Bean
    protected UserDetailsService us() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("javaboy").password("{noop}123").roles("foo", "bar").build());
        return manager;
    }

    @Configuration
    @Order(1)
    static class FooWebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/**/*.html");
        }

        private UserDetailsService us1() {
            InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
            manager.createUser(User.withUsername("foo").password("{noop}123").roles("foo").build());
            return manager;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/foo/**")
                    .authorizeRequests()
                    .anyRequest().hasRole("foo")
                    .and()
                    .formLogin()
                    .loginPage("/foo/login.html")
                    .loginProcessingUrl("/foo/login")
                    .permitAll()
                    .and()
                    .userDetailsService(us1())
                    .csrf().disable();
        }
    }

    @Configuration
    @Order(2)
    static class BarWebSecurityConfig extends WebSecurityConfigurerAdapter {

        private UserDetailsService us2() {
            InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
            manager.createUser(User.withUsername("bar").password("{noop}123").roles("bar").build());
            return manager;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/bar/**")
                    .authorizeRequests()
                    .anyRequest().hasRole("bar")
                    .and()
                    .formLogin()
                    .loginPage("/bar/login.html")
                    .loginProcessingUrl("/bar/login")
                    .permitAll()
                    .and()
                    .userDetailsService(us2())
                    .csrf().disable();
        }
    }

}
