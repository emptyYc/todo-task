package top.codx.todotask.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.codx.todotask.annotation.Log;
import top.codx.todotask.annotation.MultiRequestBody;
import top.codx.todotask.common.ResponseResult;
import top.codx.todotask.common.constant.SysConstant;
import top.codx.todotask.common.enums.BusinessType;
import top.codx.todotask.model.entity.SysUser;
import top.codx.todotask.service.LoginService;

/**
 * 登录相关Controller
 *
 * @author Liuch
 * @since 2023-04-03 22:56
 */
@Api(tags = "登录功能接口")
@RestController
@RequestMapping(SysConstant.SYS_API_URL_PREFIX + "user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @ApiOperation("登录")
    @PostMapping("/login")
    @Log(title = "用户登录", businessType = BusinessType.OTHER)
    public ResponseResult login(@MultiRequestBody String userName, @MultiRequestBody String cipher) {
        return loginService.login(userName, cipher);
    }

    @ApiOperation("退出登录")
    @PostMapping("/logout")
    @Log(title = "用户退出", businessType = BusinessType.OTHER)
    public ResponseResult logout() {
        return loginService.logout();
    }

    @ApiOperation("用户注册-获取验证码")
    @GetMapping("/register/captcha")
    @Log(title = "用户注册", businessType = BusinessType.OTHER)
    public ResponseResult registerCode(@RequestBody String email) {
        return loginService.registerCode(email);
    }

    @ApiOperation("用户注册")
    @PostMapping("/register")
    @Log(title = "用户注册", businessType = BusinessType.OTHER)
    public ResponseResult register(@MultiRequestBody SysUser sysUser, @MultiRequestBody String uuid, @MultiRequestBody String code) {
        return loginService.register(sysUser, uuid, code);
    }
}
