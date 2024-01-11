package top.codx.todotask.handler;

import cn.hutool.json.JSONUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import top.codx.todotask.common.ResponseResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 授权异常处理器
 *
 * @author Liuch
 * @since 2023-04-03 22:46
 */
@Component
public class AccessExceptionHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // 处理异常,返回错误信息
        ResponseResult err = ResponseResult.err(40015, "授权异常");
        String json = JSONUtil.toJsonStr(err);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(json);
    }
}
