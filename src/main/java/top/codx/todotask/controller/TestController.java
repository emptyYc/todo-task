package top.codx.todotask.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.codx.todotask.annotation.Log;
import top.codx.todotask.common.ResponseResult;
import top.codx.todotask.common.constant.SysConstant;
import top.codx.todotask.common.enums.BusinessType;

import java.util.Collection;

/**
 * 测试相关Controller
 *
 * @author Liuch
 * @since 2023-04-03 20:52
 */
@Api(tags = "测试功能接口")
@RestController
@RequestMapping(SysConstant.SYS_API_URL_PREFIX + "test")
public class TestController {

    @GetMapping("/conn")
    @ApiOperation("通用连通性测试")
    @Log(title = "测试", businessType = BusinessType.SELECT)
    public ResponseResult conn() {
        return ResponseResult.ok();
    }

    @GetMapping("/token")
    @ApiOperation("token测试")
    @Log(title = "测试", businessType = BusinessType.SELECT)
    public ResponseResult token() {
        return ResponseResult.ok();
    }

    @GetMapping("/test1")
    @ApiOperation("测试接口1-获取当前用户拥有的权限")
    @Log(title = "测试", businessType = BusinessType.SELECT)
    public ResponseResult test1() {
        System.out.println("===============");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        return ResponseResult.ok(authorities);
    }

}
