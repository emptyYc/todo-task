package top.codx.todotask.aspectj;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.id.NanoId;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.codx.todotask.annotation.Log;
import top.codx.todotask.common.util.AuthorUtil;
import top.codx.todotask.common.util.IPUtil;
import top.codx.todotask.model.entity.SysApiLog;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 接口调用日志实现
 *
 * @author Liuch
 * @since 2023-05-20 17:46
 */
@Aspect
@Component
public class LogAspect {

    static Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("@annotation(top.codx.todotask.annotation.Log)")
    void pointcut() {
    }

    @Around("pointcut()")
    private Object testAop(ProceedingJoinPoint point) throws Throwable {
        SysApiLog sysApiLog = new SysApiLog();
        // 获取当前请求
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        // 获取参数
        Object[] args = point.getArgs();
        // 获取目标方法
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Log log = method.getAnnotation(Log.class);
        // 执行目标方法 获取返回值
        Object result = point.proceed();
        ObjectMapper mapper = new ObjectMapper();
        // 保存日志
        // 调用者
        sysApiLog.setUserName(AuthorUtil.getUserName());
        // 调用ip
        sysApiLog.setIp(IPUtil.getIP(request));
        // 请求路径
        sysApiLog.setUrl(request.getRequestURI());
        // 操作title
        sysApiLog.setTitle(log.title());
        // 调用的方法
        sysApiLog.setMethod(signature.getDeclaringTypeName() + "." + method.getName());
        // 请求方式
        sysApiLog.setRequestType(request.getMethod());
        // 业务类型
        sysApiLog.setType(log.businessType().toString());
        // 请求参数
        sysApiLog.setParams(mapper.writeValueAsString(args));
        // 返回结果
        sysApiLog.setResult(mapper.writeValueAsString(result));
        //创建人
        sysApiLog.setCreatedBy(AuthorUtil.getUserName());
        // 创建时间
        sysApiLog.setCreatedTime(DateUtil.date());
        //id
        sysApiLog.setId(NanoId.randomNanoId());
        // 写入日志
        logger.info(sysApiLog.toString());
        return result;
    }
}
