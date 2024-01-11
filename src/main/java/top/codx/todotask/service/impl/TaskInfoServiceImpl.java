package top.codx.todotask.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.codx.todotask.common.util.AuthorUtil;
import top.codx.todotask.common.util.EntityGeneralUtil;
import top.codx.todotask.common.util.PageReq;
import top.codx.todotask.model.entity.SysUser;
import top.codx.todotask.model.entity.TaskInfo;
import top.codx.todotask.model.mapper.TaskInfoMapper;
import top.codx.todotask.service.TaskInfoService;

import java.util.List;

/**
 * 任务信息表;(TASK_INFO)表服务实现类
 *
 * @author : Liuch
 * @date : 2023-12-28
 */
@Service
public class TaskInfoServiceImpl implements TaskInfoService {
    @Autowired
    private TaskInfoMapper taskInfoMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TaskInfo queryById(String id) {
        return taskInfoMapper.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param taskInfo 筛选条件
     * @param pageReq  分页对象
     * @return 查询结果
     */
    @Override
    public PageInfo<TaskInfo> paginQuery(TaskInfo taskInfo, PageReq pageReq) {
        PageHelper.startPage(pageReq.getPageNum(), pageReq.getPageSize());
        List<TaskInfo> taskInfoMap = taskInfoMapper.queryAllByLimit(taskInfo);
        return new PageInfo<>(taskInfoMap);
    }

    /**
     * 新增数据
     *
     * @param taskInfo 实例对象
     * @return 实例对象
     */
    @Override
    public TaskInfo insert(TaskInfo taskInfo) {
        SysUser user = AuthorUtil.getUser().getSysUser();
        taskInfo.setUserId(user.getId());
        taskInfo.setUserCode(user.getUserCode());
        taskInfo.setUserName(user.getUserName());
        EntityGeneralUtil.AssignmentInsert(taskInfo);
        taskInfoMapper.insert(taskInfo);
        return taskInfo;
    }

    /**
     * 更新数据
     *
     * @param taskInfo 实例对象
     * @return 实例对象
     */
    @Override
    public TaskInfo update(TaskInfo taskInfo) {
        EntityGeneralUtil.AssignmentUpdate(taskInfo);
        taskInfoMapper.update(taskInfo);
        return queryById(taskInfo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        int total = taskInfoMapper.deleteById(id);
        return total > 0;
    }

    /**
     * 点赞
     *
     * @param id id
     * @return {@link Object}
     */
    @Override
    public Object like(String id) {
        TaskInfo taskInfo = queryById(id);
        if (taskInfo != null) {
            taskInfo.setLikeNum(String.valueOf(Integer.parseInt(taskInfo.getLikeNum()) + 1));
            update(taskInfo);
            return "点赞成功";
        }
        return "点赞失败";
    }
}