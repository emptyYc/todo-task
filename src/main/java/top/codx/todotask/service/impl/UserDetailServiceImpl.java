package top.codx.todotask.service.impl;

import cn.hutool.core.util.ObjUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import top.codx.todotask.exception.SysException;
import top.codx.todotask.model.entity.LoginUser;
import top.codx.todotask.model.entity.SysUser;
import top.codx.todotask.model.mapper.SysUserMapper;

import java.util.List;

/**
 * UserDetailsService实现类, 实现从数据库中获取用户信息
 *
 * @author Liuch
 * @since 2023-04-03 22:30
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) {
        // 查询用户信息
        SysUser query = new SysUser();
        query.setUserName(username);
        SysUser sysUser = sysUserMapper.queryAllByLimit(query);
        if (ObjUtil.isNull(sysUser)) {
            throw new SysException(40016, "不存在此用户");
        }
        if ("1".equals(sysUser.getIsDel())) {
            throw new SysException(40016, "此用户已注销");
        }
        if ("0".equals(sysUser.getIsEnable())) {
            throw new SysException(40016, "无权限");
        }
        // 查询对应的权限信息
        List<String> purview = sysUserMapper.getAllPurviewByUserId(sysUser.getId());
        // 把数据封装成UserDetail返回
        return new LoginUser(sysUser, purview);
    }
}
