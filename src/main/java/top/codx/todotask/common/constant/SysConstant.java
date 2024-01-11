package top.codx.todotask.common.constant;

import java.text.SimpleDateFormat;

/**
 * 系统常量类
 *
 * @author liuch
 * @date 2023-12-28 16:03:06
 */
public interface SysConstant {
    /**
     * 时间格式化基本形式
     */
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 数据库相关 - 是否删除   0:未删  1：已删除
     */
    String DATABASE_DATA_IS_DEL_0 = "0";

    String DATABASE_DATA_IS_DEL_1 = "1";


    /**
     * 数据库相关 - 是否作废   0:未作废  1：已作废
     */

    String DATABASE_DATA_IS_VOID_0 = "0";
    String DATABASE_DATA_IS_VOID_1 = "1";

    /**
     * 数据库相关 - 是否启用   0:停用 1：启用
     */
    String DATABASE_DATA_DISABLE_0 = "0";

    String DATABASE_DATA_ENABLE_1 = "1";

    /**
     * 数据库相关 - 自由态 0; 审核中 1
     */

    String DATABASE_DATA_UNREVIEW_0 = "0";

    String DATABASE_DATA_REVIEWING_1 = "1";

    /**
     * 系统模块接口前缀
     */
    String SYS_API_URL_PREFIX = "/api/todoTask/";

    /**
     * 文件相关参数.开始================================================================================
     */
    /**
     * 50兆
     */
    int FILE_MAX_SIZE = 60 * 1024 * 1024;


    /**
     * 文件相关参数.结束================================================================================
     */


}
