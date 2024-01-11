package top.codx.todotask.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import top.codx.todotask.filter.JWTFilter;
import top.codx.todotask.handler.AccessExceptionHandler;
import top.codx.todotask.handler.AuthExceptionHandler;

/**
 * Security配置类
 *
 * @author Liuch
 * @since 2023-04-03 18:06
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private JWTFilter jwtFilter;
    @Autowired
    private AuthExceptionHandler authExceptionHandler;
    @Autowired
    private AccessExceptionHandler accessExceptionHandler;


    /**
     * 配置密码加密方式
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 默认配置
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // 关闭csrf
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 设置不通过Session获取SecurityContext
                .and()
                .authorizeRequests()
                .antMatchers("/api/todoTask/user/login",
                        "/favicon.ico",
                        "/",
                        "/api/todoTask/test/conn",
                        "/api/todoTask/user/register/captcha",
                        "/api/todoTask/user/register",
                        "/api/todoTask/taskInfo/**",
                        "/index.html",
                        "/js/**",
                        "/css/**").permitAll() // 允许登陆接口匿名访问
                .antMatchers("/swagger-ui.html",
                        "/swagger-ui/*",
                        "/swagger-resources/**",
                        "/v2/api-docs",
                        "/v3/api-docs",
                        "/doc.html",
                        "/webjars/**").permitAll()// 允许swagger
                .antMatchers("/druid/**").permitAll() // 允许druid
                .antMatchers("/actuator/**").permitAll() // 允许actuator
                .anyRequest().authenticated(); // 其他所有的请求都需要登录认证

        // 添加JWT过滤器
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        // 配置异常处理器
        http.exceptionHandling()
                .authenticationEntryPoint(authExceptionHandler)
                .accessDeniedHandler(accessExceptionHandler);
        // 设置允许跨域
        http.cors();
        return http.build();
    }

    /**
     * 认证管理器
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
