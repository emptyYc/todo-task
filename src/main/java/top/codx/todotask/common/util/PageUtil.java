package top.codx.todotask.common.util;

import com.github.pagehelper.PageHelper;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 分页工具
 */
public class PageUtil {

    /**
     * 最大分页大小
     */
    static int MAX_PAGE_SIZE = 100;
    /**
     * 默认分页
     */
    static int DEFAULT_PAGE_NUM = 1;
    static int DEFAULT_PAGE_SIZE = 10;

    /**
     * 开启分页
     */
    public static void startPage() {
        try {
            int pageNum = DEFAULT_PAGE_NUM;
            int pageSize = DEFAULT_PAGE_SIZE;
            HttpServletRequest request = IPUtil.getRequest();
            String num = request.getParameter("pageNum");
            if (StringUtils.hasText(num)) {
                pageNum = Integer.parseInt(num);
            }
            String size = request.getParameter("pageSize");
            if (StringUtils.hasText(size)) {
                pageSize = Integer.parseInt(size);
            }

            PageHelper.startPage(pageNum, pageSize);
        } catch (Exception e) {
            PageHelper.startPage(DEFAULT_PAGE_NUM, DEFAULT_PAGE_SIZE);
        }
    }

    /**
     * 指定分页大小
     */
    public static void startPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
    }
}
