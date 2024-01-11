package top.codx.todotask.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.codx.todotask.model.entity.TaskInfo;

import java.util.List;

/**
 * 任务信息表;(TASK_INFO)表数据库访问层
 *
 * @author : Liuch
 * @date : 2023-12-28
 */
@Mapper
public interface TaskInfoMapper {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TaskInfo queryById(String id);

    /**
     * 分页查询指定行数据
     *
     * @param taskInfo 查询条件
     * @return 对象列表
     */
    List<TaskInfo> queryAllByLimit(TaskInfo taskInfo);

    /**
     * 统计总行数
     *
     * @param taskInfo 查询条件
     * @return 总行数
     */
    long count(TaskInfo taskInfo);

    /**
     * 新增数据
     *
     * @param taskInfo 实例对象
     * @return 影响行数
     */
    int insert(TaskInfo taskInfo);

    /**
     * 批量新增数据
     *
     * @param entities List<TaskInfo> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<TaskInfo> entities);

    /**
     * 更新数据
     *
     * @param taskInfo 实例对象
     * @return 影响行数
     */
    int update(TaskInfo taskInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);
}