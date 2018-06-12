package sachonline.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    DataSource dataSource;

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        super.configure(web);
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("admin")
//                        .password("admin")
//                        .roles("ADMIN")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }
//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withDefaultPasswordEncoder().username("admin").password("admin")
////                .passwordEncoder(s -> passwordEncoder().encode(s))
//                .roles("ADMIN").build());
////        System.out.println(passwordEncoder().encode("admin"));
//        manager.createUser(User.withDefaultPasswordEncoder().username("test").password("test")
////                .passwordEncoder(s -> passwordEncoder().encode(s))
//                .roles("USER").build());
//
////        JdbcUserDetailsManager jdbcManager = new JdbcUserDetailsManager();
//
//        return manager;
//    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        PasswordEncoder delegatingPasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        auth.inMemoryAuthentication().passwordEncoder(delegatingPasswordEncoder);
        User.UserBuilder users = User.withDefaultPasswordEncoder();

        auth.inMemoryAuthentication().withUser(users.username("admin").password("admin").roles("ADMIN"))
                .withUser(users.username("test").password("test").roles("USER"));


    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
////        http.authorizeRequests().regexMatchers("/chief/.*").hasRole("USER")
////        BasicAuthenticationFilter filter = new BasicAuthenticationFilter(super.authenticationManagerBean());
//
        http.authorizeRequests()
                .regexMatchers("/js/.*", "/css/.*", "/img/.*")
                .permitAll()
                .regexMatchers("/user/.*")
                .authenticated()
                .regexMatchers("/sach/index")
                .permitAll()
                .regexMatchers("/admin/.*", "/sach/update/.*", "/sach/create")
//                .access("hasRole('ADMIN') and principal.username='admin'").anyRequest()
                .hasRole("ADMIN")
        ;
//                .and().httpBasic()
//                .and().requiresChannel().anyRequest()
//                .requiresSecure()
//                .and().exceptionHandling().authenticationEntryPoint(customDigestAuthenticationEntryPoint)
//                .accessDeniedPage("/error")
//                .and().requiresChannel().anyRequest().requiresSecure()
//                .and().addFilter(digestAuthenticationFilter());
//
        http.formLogin()
                .loginPage("/user/login")
                .permitAll()
                .loginProcessingUrl("/processLogin")
                .passwordParameter("password")
                .usernameParameter("username")
                .defaultSuccessUrl("/admin/sach")
        ;

        http.logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/sach/index")
//                .invalidateHttpSession(true) //true by default
//                .addLogoutHandler(new SecurityContextLogoutHandler())
//                .deleteCookies("JSESSIONID")
                .permitAll()
        ;
//        http
//                .authorizeRequests()
//                .antMatchers("/", "/sach/index").permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();

    }

    public DigestAuthenticationFilter digestAuthenticationFilter() {
        DigestAuthenticationFilter digestAuthenticationFilter = new DigestAuthenticationFilter();
        digestAuthenticationFilter.setAuthenticationEntryPoint(customDigestAuthenticationEntryPoint);
        digestAuthenticationFilter.setUserDetailsService(userDetailsService());
        return digestAuthenticationFilter;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN")
                .and().withUser("test").password("test").roles("USER");
    }

    @Autowired
    CustomDigestAuthenticationEntryPoint customDigestAuthenticationEntryPoint;
}
