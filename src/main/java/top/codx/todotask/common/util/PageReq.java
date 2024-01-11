package top.codx.todotask.common.util;

import org.apache.commons.lang.StringUtils;

public class PageReq {

    private Integer pageNum = 1;

    private Integer pageSize = 10;

    private String orderByColumn;
    //0降序 1升序
    private String sort = "0";

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


    /**
     * @return orderByColumn
     */
    public String getOrderByColumn() {
        return orderByColumn;
    }

    /**
     * @param orderByColumn 要设置的 orderByColumn
     */
    public void setOrderByColumn(String orderByColumn) {
        this.orderByColumn = orderByColumn;
    }

    /**
     * @return sort
     */
    public String getSort() {
        return sort;
    }

    /**
     * @param sort 要设置的 sort
     */
    public void setSort(String sort) {
        this.sort = sort;
    }

    /**
     * 排序
     *
     * @param orderByColumn 排序字段
     * @param sort          升降序
     */
    public static String getOrderBy(String orderByColumn, String sort) {

        if (StringUtils.isNotBlank(sort)) {
            if ("0".equals(sort)) {
                sort = "desc";
            }
            if ("1".equals(sort)) {
                sort = "asc";
            }
        } else {
            sort = "desc";
        }
        if (StringUtils.isBlank(orderByColumn)) {
            orderByColumn = "updatedTime";
        }
        orderByColumn = UnderlineToCamelUtils.camelToUnderline(orderByColumn);

        return orderByColumn + " " + sort;
    }

    /**
     * 排序
     *
     * @param orderByColumn 排序字段
     * @param sort          升降序
     * @param append        拼接字符串用来多表排序的
     */
    public static String getOrderBy(String orderByColumn, String sort, String append) {

        if (StringUtils.isNotBlank(sort)) {
            if ("0".equals(sort)) {
                sort = "desc";
            }
            if ("1".equals(sort)) {
                sort = "asc";
            }
        } else {
            sort = "desc";
        }
        if (StringUtils.isBlank(orderByColumn)) {
            orderByColumn = "updatedTime";
        }
        orderByColumn = UnderlineToCamelUtils.camelToUnderline(orderByColumn);
        orderByColumn = append + orderByColumn;

        return orderByColumn + " " + sort;
    }
    
    /**
     * 排序
     *
     * @param orderByColumn 排序字段
     * @param sort          升降序
     * @param append        拼接字符串用来多表排序的
     */
    public static String getOrderByAppendLast(String orderByColumn, String sort, String append) {

        if (StringUtils.isNotBlank(sort)) {
            if ("0".equals(sort)) {
                sort = "desc";
            }
            if ("1".equals(sort)) {
                sort = "asc";
            }
        } else {
            sort = "desc";
        }
        //orderByColumn = UnderlineToCamelUtils.camelToUnderline(orderByColumn);
        return orderByColumn + " " + sort + " " + append;
    }
}
