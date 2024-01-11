package top.codx.todotask.common.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.id.NanoId;
import lombok.Data;

import java.sql.Timestamp;

public class EntityGeneralUtil {
    public static EntityGenera general(String flag) {
        // 创建通用对象
        EntityGenera entityGenera = new EntityGenera();
        String userName = AuthorUtil.getUserName();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        entityGenera.setUpdatedBy(userName);
        entityGenera.setUpdatedTime(now);
        if (flag.equals("insert")) {
            // 设置创建人
            entityGenera.setCreatedBy(userName);
            // 设置创建时间
            entityGenera.setCreatedTime(now);
            // 生成id
            entityGenera.setId(NanoId.randomNanoId());
            // 数据默认生效
            entityGenera.setIsDel("0");
            entityGenera.setIsEnable("1");
        }
        return entityGenera;
    }

    /**
     * 通用添加方法
     *
     * @param t
     * @param <T>
     */
    public static <T> void AssignmentInsert(T t) {
        BeanUtil.copyProperties(general("insert"), t);
    }

    /**
     * 通用修改方法
     *
     * @param t
     * @param <T>
     */
    public static <T> void AssignmentUpdate(T t) {
        BeanUtil.copyProperties(general("update"), t, "id", "createdBy", "createdTime", "isDel");
    }

    /**
     * 通用实体对象
     */
    @Data
    static class EntityGenera {
        private String id;
        private String createdBy;
        private Timestamp createdTime;
        private String updatedBy;
        private Timestamp updatedTime;
        private String isDel;
        private String isEnable;
    }

}
