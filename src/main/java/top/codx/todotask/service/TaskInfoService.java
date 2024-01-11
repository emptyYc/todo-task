package top.codx.todotask.service;


import com.github.pagehelper.PageInfo;
import top.codx.todotask.common.util.PageReq;
import top.codx.todotask.model.entity.TaskInfo;

/**
 * 任务信息表;(TASK_INFO)表服务接口
 *
 * @author : Liuch
 * @date : 2023-12-28
 */
public interface TaskInfoService {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TaskInfo queryById(String id);

    /**
     * 分页查询
     *
     * @param taskInfo 筛选条件
     * @param pageReq  分页对象
     * @return 查询结果
     */
    PageInfo<TaskInfo> paginQuery(TaskInfo taskInfo, PageReq pageReq);

    /**
     * 新增数据
     *
     * @param taskInfo 实例对象
     * @return 实例对象
     */
    TaskInfo insert(TaskInfo taskInfo);

    /**
     * 更新数据
     *
     * @param taskInfo 实例对象
     * @return 实例对象
     */
    TaskInfo update(TaskInfo taskInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    /**
     * 点赞
     *
     * @param id id
     * @return {@link Object}
     */
    Object like(String id);
}