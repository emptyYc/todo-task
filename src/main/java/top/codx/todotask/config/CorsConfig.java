package top.codx.todotask.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置允许跨域
 *
 * @author Liuch
 * @since 2023-04-03 23:19
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 允许跨域的路径
        registry.addMapping("/**")
                // 允许跨域的域名
                .allowedOriginPatterns("*")
                // 是否允许cookie
                .allowCredentials(true)
                // 允许的请求方式
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                // 允许的请求头
                .allowedHeaders("*")
                // 允许跨域时间
                .maxAge(3600);
    }
}
