package top.codx.todotask.service;


import top.codx.todotask.common.ResponseResult;
import top.codx.todotask.model.entity.SysUser;

/**
 * 用户service类
 *
 * @author Liuch
 * @since 2023-04-03 22:58
 */
public interface LoginService {
    ResponseResult login(String userName, String cipher);

    ResponseResult logout();

    ResponseResult registerCode(String email);

    ResponseResult register(SysUser sysUser, String uuid, String code);

}
