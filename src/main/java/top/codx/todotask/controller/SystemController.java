package top.codx.todotask.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.codx.todotask.common.ResponseResult;
import top.codx.todotask.common.constant.SysConstant;
import top.codx.todotask.service.SystemService;

/**
 * 类的描述
 *
 * @author Liuch
 * @since 2023-06-02 23:21
 */
@Api(tags = "系统相关接口")
@RestController
@RequestMapping(SysConstant.SYS_API_URL_PREFIX + "sys")
public class SystemController {

    @Autowired
    private SystemService systemService;

    @GetMapping("/userinfo")
    @ApiOperation("获取当前用户信息")
    public ResponseResult userinfo() {
        return systemService.userinfo();
    }
}
