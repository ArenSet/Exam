package am.hitech.config;

import am.hitech.util.MyPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /*public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user1").password("123").roles("CUSTOMER").and()
                .withUser("user2").password("123").roles("ADMIN").and()
                .passwordEncoder(new MyPasswordEncoder());

    }*/

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MyUserDetailsService userDetailsService;

    public void configure(HttpSecurity http) throws Exception {
        http
                //.antMatcher("/user/**")//close specified urls
                .httpBasic().and()//for basic authentication
                .csrf().disable()//some error cases
                .cors().disable()//for cors errors, ask for access to make request to other server(http method - OPTION)
                .headers().frameOptions().disable()//close frames from other websites
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//to save(STATELESS) session or not(ALWAYS)
                .and().authorizeRequests()
                .antMatchers("/user/create").permitAll()
                /*.antMatchers(HttpMethod.POST, "/user/**").permitAll()
                .antMatchers("/id").hasAnyAuthority()
                .antMatchers("/find-all").hasAuthority("ADMIN")//not secure urls
                .antMatchers("/search").hasAnyAuthority()
                .antMatchers("/name").hasAnyAuthority()
                .antMatchers("/edit").hasAnyAuthority()
                .antMatchers("/change-status").hasAnyAuthority()*/
                .anyRequest().authenticated();
    }


    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
}
