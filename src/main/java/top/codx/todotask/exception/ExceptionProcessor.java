package top.codx.todotask.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import top.codx.todotask.common.ResponseResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 统一异常处理器
 *
 * @author Liuch
 * @since 2023-04-03 15:40
 */
@Order(-999)
@Slf4j
@ControllerAdvice
public class ExceptionProcessor {

    /**
     * 统一异常处理方法<br>
     * 将异常进行统一处理,并将结果返回给前端
     *
     * @param request  {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @param handler  {@link Object}
     * @param ex       {@link Exception}
     * @return {@link ResponseResult}
     */
    @ResponseBody
    @ExceptionHandler()
    public ResponseResult resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        log.warn("发生了错误", ex);
        if (ex instanceof SysException) {
            // 自定义异常
            return ResponseResult.err(((SysException) ex).getCode(), ex.getMessage());
        } else if (ex instanceof BadCredentialsException) {
            // 授权异常
            return ResponseResult.err(40014, ex.getMessage());
        } else if (ex instanceof AccessDeniedException) {
            // 权限异常
            return ResponseResult.err(40015, ex.getMessage());
        }
        return ResponseResult.err(ex.getMessage());
    }

    /**
     * HttpMessageNotReadableException<br>
     *
     * @param request  {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @param handler  {@link Object}
     * @param ex       {@link Exception}
     * @return {@link ResponseResult}
     */
    @ResponseBody
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseResult httpMessageNotReadableException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        return ResponseResult.err("参数缺失");
    }

    /**
     * @Validated 校验错误异常处理
     */
    @ResponseStatus(code = HttpStatus.OK)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseResult handler(MethodArgumentNotValidException e) throws IOException {
        BindingResult bindingResult = e.getBindingResult();
        ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
        String messages = objectError.getDefaultMessage();
        return ResponseResult.err(messages);
    }

    /**
     * @Validated 校验错误异常处理
     */
    @ResponseStatus(code = HttpStatus.OK)
    @ExceptionHandler(value = SysException.class)
    @ResponseBody
    public ResponseResult handler(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
        String messages = objectError.getDefaultMessage();
        return ResponseResult.err(messages);
    }

}
