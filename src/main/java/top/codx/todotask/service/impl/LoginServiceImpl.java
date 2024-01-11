package top.codx.todotask.service.impl;

import cn.hutool.core.lang.id.NanoId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.codx.todotask.common.ResponseResult;
import top.codx.todotask.common.util.EntityGeneralUtil;
import top.codx.todotask.common.util.JWTUtils;
import top.codx.todotask.common.util.RedisUtils;
import top.codx.todotask.model.entity.LoginUser;
import top.codx.todotask.model.entity.SysUser;
import top.codx.todotask.model.mapper.SysUserMapper;
import top.codx.todotask.service.LoginService;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户service类实现类
 *
 * @author Liuch
 * @since 2023-04-03 22:59
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${token.expiration}")
    private Integer tokenExpiration;

    /**
     * 登录
     *
     * @param username {@link String}
     * @param password {@link String}
     * @retrun {@link ResponseResult}
     */
    @Override
    public ResponseResult login(String userName, String cipher) {
        // 使用authenticate进行认证
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userName, cipher);
        Authentication authenticate = authenticationManager.authenticate(authentication);
        // 认证没通过,给出提示
        if (authenticate == null) {
            return ResponseResult.err(40013, "登录失败,请检查用户名和密码");
        }
        // 认证通过,使用userid生成token,返回token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = String.valueOf(loginUser.getSysUser().getId());
        String token = JWTUtils.createToken("userId", userId, tokenExpiration);
        // 把用户信息存入redis
        redisUtils.setBean("login:" + userId, loginUser, tokenExpiration);
        // 返回token给前端
        Map<String, Object> result = new HashMap<>();
        result.put("token", token);
        return ResponseResult.ok(result);
    }

    /**
     * 退出
     *
     * @retrun {@link ResponseResult}
     */
    @Override
    public ResponseResult logout() {
        // 获取用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String id = loginUser.getSysUser().getId();
        // 从redis中删除用户信息
        redisUtils.delKey("login:" + id);
        return ResponseResult.ok();
    }

    /**
     * 用户注册-获取验证码
     *
     * @param email 用户邮箱
     * @return 结果
     */
    @Override
    public ResponseResult registerCode(String email) {
        // 查询邮箱是否被占用
        SysUser query = new SysUser();
        query.setUserEmail(email);
        SysUser sysUser = sysUserMapper.queryAllByLimit(query);
        if (sysUser != null) {
            return ResponseResult.err("邮箱已被占用");
        }
        // 邮箱正则
        String regex = "^[A-Za-z0-9]+([-._][A-Za-z0-9]+)*@[A-Za-z0-9]+(-[A-Za-z0-9]+)*(\\.[A-Za-z]{2,6}|[A-Za-z]{2,4}\\.[A-Za-z]{2,3})$";
        if (!StringUtils.hasText(email) || !email.matches(regex)) {
            return ResponseResult.err("邮箱为空或邮箱不合法");
        }
        // 生成验证码
        String code = JWTUtils.captcha();
        //生成随机id
        String uid = NanoId.randomNanoId();
        // 存入redis,并设置5分钟过期时间
        redisUtils.setString("code:" + uid, code, 300L);
        HashMap<String, Object> map = new HashMap<>();
        map.put("uid", uid);
        //TODO 邮箱发送，暂时先不用
        map.put("code", code);
        return ResponseResult.ok(map);
    }

    /**
     * 用户注册
     *
     * @return 操作结果
     */
    @Override
    public ResponseResult register(SysUser sysUser, String uid, String code) {
        // 查询用户是否已经注册
        SysUser query = new SysUser();
        query.setUserEmail(sysUser.getUserName());
        SysUser user = sysUserMapper.queryAllByLimit(query);
        if (user != null) {
            return ResponseResult.err("用户已存在");
        }

        // 用户名正则,4到16位（字母，数字，下划线）
        String regex = "^[a-zA-Z0-9_-]{4,16}$";
        if (!StringUtils.hasText(sysUser.getUserName()) || !sysUser.getUserName().matches(regex)) {
            return ResponseResult.err("用户名不合法");
        }

        // 验证码
        String redisCode = redisUtils.getString("code:" + uid);
        if (!StringUtils.hasText(redisCode) || !redisCode.equalsIgnoreCase(code)) {
            return ResponseResult.err("验证码错误");
        }

        // 密码校验,至少包含一个字母和数字, 6-20位
        String pwdRegex = "^(?=.*[a-zA-Z])(?=.*\\d).{6,20}$";
        if (!StringUtils.hasText(sysUser.getUserPasswd()) || !sysUser.getUserPasswd().matches(pwdRegex)) {
            return ResponseResult.err("密码不合法");
        }

        redisUtils.delKey("code:" + uid);

        // 添加用户, 用户密码加密
        sysUser.setUserPasswd(bCryptPasswordEncoder.encode(sysUser.getUserPasswd()));
        //通用新增
        EntityGeneralUtil.AssignmentInsert(sysUser);
        sysUserMapper.insert(sysUser);
        return ResponseResult.ok();
    }
}
