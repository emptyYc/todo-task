package top.codx.todotask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.codx.todotask.common.ResponseResult;
import top.codx.todotask.common.util.IPUtil;
import top.codx.todotask.model.entity.SysUser;
import top.codx.todotask.model.mapper.SysUserMapper;
import top.codx.todotask.service.SystemService;

/**
 * 系统相关
 *
 * @author Liuch
 * @since 2023-06-02 23:30
 */
@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    private SysUserMapper sysUserMapper;


    /**
     * 获取当前用户信息
     *
     * @return ResponseResult
     */
    @Override
    public ResponseResult userinfo() {
        String userId = String.valueOf(IPUtil.getRequest().getAttribute("userId"));
        //获取除了密码外的用户信息
        SysUser sysUser = sysUserMapper.selectByPrimaryKeyExcludePassword(userId);
        return ResponseResult.ok(sysUser);
    }
}
