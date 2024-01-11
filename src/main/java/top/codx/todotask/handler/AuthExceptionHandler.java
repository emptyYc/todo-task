package top.codx.todotask.handler;

import cn.hutool.json.JSONUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import top.codx.todotask.common.ResponseResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证异常处理器
 *
 * @author Liuch
 * @since 2023-04-03 22:36
 */
@Component
public class AuthExceptionHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // 处理异常,返回错误信息
        ResponseResult err = ResponseResult.err(40014, "认证异常");
        String json = JSONUtil.toJsonStr(err);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(json);
    }
}
