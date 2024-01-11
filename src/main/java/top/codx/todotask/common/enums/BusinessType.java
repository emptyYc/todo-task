package top.codx.todotask.common.enums;

/**
 * 接口业务类型枚举
 *
 * @author Liuch
 * @since 2023-05-20 17:39
 */
public enum BusinessType {
    /**
     * 其它
     */
    OTHER("其它"),
    /**
     * 新增
     */
    INSERT("新增"),

    /**
     * 修改
     */
    UPDATE("修改"),

    /**
     * 删除
     */
    DELETE("删除"),

    /**
     * 授权
     */
    GRANT("授权"),

    /**
     * 导出
     */
    EXPORT("导出"),

    /**
     * 导入
     */
    IMPORT("导入"),

    /**
     * 强退
     */
    FORCE("强退"),

    /**
     * 生成代码
     */
    GENCODE("生成代码"),

    /**
     * 清空数据
     */
    CLEAN("清空数据"),
    /**
     * 查询
     */
    SELECT("查询");

    final String businessName;

    BusinessType(String businessName) {
        this.businessName = businessName;
    }

    @Override
    public String toString() {
        return businessName;
    }
}
