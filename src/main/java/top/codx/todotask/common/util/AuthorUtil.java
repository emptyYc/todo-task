package top.codx.todotask.common.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import top.codx.todotask.model.entity.LoginUser;

/**
 * 登录用户信息
 *
 * @author Liuch
 * @since 2023-04-28 10:31
 */
public class AuthorUtil {
    /**
     * 获取当前用户名称
     *
     * @return {@link String}
     */
    public static String getUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    /**
     * 获取当前登录用户
     *
     * @return {@link LoginUser}
     */
    public static LoginUser getUser() {
        return (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
