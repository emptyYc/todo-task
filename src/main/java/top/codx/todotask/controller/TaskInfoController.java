package top.codx.todotask.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.codx.todotask.annotation.Log;
import top.codx.todotask.annotation.MultiRequestBody;
import top.codx.todotask.common.ResponseResult;
import top.codx.todotask.common.constant.SysConstant;
import top.codx.todotask.common.enums.BusinessType;
import top.codx.todotask.common.util.PageReq;
import top.codx.todotask.model.entity.TaskInfo;
import top.codx.todotask.service.TaskInfoService;

/**
 * 任务信息表;(TASK_INFO)表控制层
 *
 * @author : Liuch
 * @date : 2023-12-28
 */
@Api(tags = "任务信息表对象功能接口")
@RestController
@RequestMapping(SysConstant.SYS_API_URL_PREFIX + "taskInfo/")
public class TaskInfoController {
    @Autowired
    private TaskInfoService taskInfoService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{id}")
    @Log(title = "任务信息表查询单条数据", businessType = BusinessType.SELECT)
    public ResponseResult queryById(@PathVariable("id") String id) {
        return ResponseResult.ok(taskInfoService.queryById(id));
    }

    /**
     * 分页查询
     *
     * @param taskInfo 筛选条件
     * @param pageReq  分页对象
     * @return 查询结果
     */
    @ApiOperation("分页查询")
    @PostMapping("paginQuery")
    @Log(title = "任务信息表分页查询数据", businessType = BusinessType.SELECT)
    public ResponseResult paginQuery(@MultiRequestBody TaskInfo taskInfo, @MultiRequestBody PageReq pageReq) {
        if (pageReq == null) {
            pageReq = new PageReq();
        }
        return ResponseResult.ok(taskInfoService.paginQuery(taskInfo, pageReq));
    }

    /**
     * 新增数据
     *
     * @param taskInfo 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping("add")
    @Log(title = "任务信息表新增数据", businessType = BusinessType.INSERT)
    public ResponseResult add(@Validated @RequestBody TaskInfo taskInfo) {
        return ResponseResult.ok(taskInfoService.insert(taskInfo));
    }

    /**
     * 点赞计划
     *
     * @param id 实例对象
     * @return 实例对象
     */
    @ApiOperation("点赞计划")
    @PostMapping("like")
    @Log(title = "任务信息表数据点赞", businessType = BusinessType.INSERT)
    public ResponseResult like(@Validated @RequestBody String id) {
        return ResponseResult.ok(taskInfoService.like(id));
    }

    /**
     * 更新数据
     *
     * @param taskInfo 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PutMapping
    @Log(title = "任务信息表更新数据", businessType = BusinessType.UPDATE)
    public ResponseResult edit(@Validated @RequestBody TaskInfo taskInfo) {
        return ResponseResult.ok(taskInfoService.update(taskInfo));
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @ApiOperation("通过主键删除数据")
    @DeleteMapping
    @Log(title = "任务信息表删除数据", businessType = BusinessType.DELETE)
    public ResponseResult deleteById(@MultiRequestBody String id) {
        return ResponseResult.ok(taskInfoService.deleteById(id));
    }
}