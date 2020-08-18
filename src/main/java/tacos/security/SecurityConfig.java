package tacos.security;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

//    @Override
//    public void configure(WebSecurity web) throws Exception
//    {
//        web.ignoring().antMatchers("/**");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests()
            .antMatchers("/design","/orders")
                .access("hasRole('ROLE_USER')")
            .antMatchers("/","/**/**","/h2-console", "/h2-console/**")
                .access("permitAll")
        .and()
            .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "select username, password, enabled from users " +
                        "where username=?")
                .authoritiesByUsernameQuery(
                        "select username, authority from authorities " +
                        "where username=?")
                .passwordEncoder(new NoEncodingPasswordEncoder());
                //.passwordEncoder(new BCryptPasswordEncoder());


//        auth.inMemoryAuthentication()
//                .withUser("user1")
//                .password("{noop}password1")
//                .authorities("ROLE_USER")
//                .and()
//                .withUser("user2")
//                .password("{noop}password2")
//                .authorities("ROLE_USER");
    }
}