package top.codx.todotask.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.codx.todotask.model.entity.SysUser;

import java.util.List;

/**
 * 用户表;(SYS_USER)表数据库访问层
 *
 * @author : Liuch
 * @date : 2023-12-28
 */
@Mapper
public interface SysUserMapper {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysUser queryById(String id);

    /**
     * 分页查询指定行数据
     *
     * @param sysUser 查询条件
     * @return 对象列表
     */
    SysUser queryAllByLimit(SysUser sysUser);

    /**
     * 统计总行数
     *
     * @param sysUser 查询条件
     * @return 总行数
     */
    long count(SysUser sysUser);

    /**
     * 新增数据
     *
     * @param sysUser 实例对象
     * @return 影响行数
     */
    int insert(SysUser sysUser);

    /**
     * 批量新增数据
     *
     * @param entities List<SysUser> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysUser> entities);

    /**
     * 更新数据
     *
     * @param sysUser 实例对象
     * @return 影响行数
     */
    int update(SysUser sysUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

    /**
     * 通过用户id获取所有权限
     *
     * @param id id
     * @return {@link List}<{@link String}>
     */
    List<String> getAllPurviewByUserId(String id);

    /**
     * 获取除了密码外的用户信息
     *
     * @param userId 用户id
     * @return {@link SysUser}
     */
    SysUser selectByPrimaryKeyExcludePassword(String userId);
}