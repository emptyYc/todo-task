package top.codx.todotask.filter;

import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import top.codx.todotask.common.ResponseResult;
import top.codx.todotask.common.util.IPUtil;
import top.codx.todotask.common.util.JWTUtils;
import top.codx.todotask.common.util.RedisUtils;
import top.codx.todotask.exception.SysException;
import top.codx.todotask.model.entity.LoginUser;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * token认证过滤器
 *
 * @author Liuch
 * @since 2023-04-03 14:05
 */
@Component
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    private RedisUtils redisUtils;

    @Value("${token.expiration}")
    private Integer tokenExpiration;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        // 获取token
        String token = request.getHeader("Authorization");
        if (!StringUtils.hasText(token) || "/user/login".equals(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }
        // 解析token,获取userId
        String userId = null;
        try {
            userId = JWTUtils.parseToken(token, "userId", false);
        } catch (Exception e) {
            if (e instanceof SysException) {
                response.getWriter().println(JSONUtil.toJsonStr(ResponseResult.err(((SysException) e).getCode(), e.getMessage())));
            } else {
                response.getWriter().println(JSONUtil.toJsonStr(ResponseResult.err()));
            }
            return;
        }
        // 从redis中获取用户信息
        LoginUser loginUser = redisUtils.getBean("login:" + userId, LoginUser.class);
        if (loginUser == null || loginUser.getSysUser() == null) {
            response.getWriter().println(JSONUtil.toJsonStr(ResponseResult.err(401, "无效的会话,或会话已过期")));
            return;
        }
        // 重新设置过期时间
        redisUtils.setBean("login:" + userId, loginUser, tokenExpiration);
        // 将用户信息存入SecurityContext
        IPUtil.getRequest().setAttribute("userId", userId);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
