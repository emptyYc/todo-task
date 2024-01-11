package top.codx.todotask.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * BMS自定义异常
 *
 * @author Liuch
 * @since 2023-04-03 11:04
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysException extends RuntimeException {

    private static final long serialVersionUID = -5103833850956753763L;
    /**
     * 错误码
     */
    private final Integer code;

    public SysException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
