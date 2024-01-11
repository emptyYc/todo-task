package top.codx.todotask.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Restful
 *
 * @author Liuch
 * @since 2023-04-02 20:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "返回体")
public class ResponseResult {

    /**
     * 成功状态码
     */
    public static final Integer SUCCESS = 200;

    /**
     * 服务器内部发生错误状态码
     */
    public static final Integer ERROR = 500;

    /**
     * 成功描述信息
     */
    public static final String SUCCESS_MSG = "success";

    /**
     * 服务器内部错误描述信息
     */
    public static final String ERROR_MSG = "服务器内部发生错误";

    /**
     * 响应状态码
     */
    @ApiModelProperty("响应状态码")
    private Integer code;
    /**
     * 响应信息
     */
    @ApiModelProperty("响应信息")
    private String msg;
    /**
     * 响应的数据
     */
    @ApiModelProperty("响应数据")
    private Object data;

    public static ResponseResult ok() {
        return new ResponseResult(SUCCESS, SUCCESS_MSG, null);
    }

    public static ResponseResult ok(Object data) {
        return new ResponseResult(SUCCESS, SUCCESS_MSG, data);
    }

    public static ResponseResult err() {
        return new ResponseResult(ERROR, ERROR_MSG, null);
    }

    public static ResponseResult err(Integer code, String msg) {
        return new ResponseResult(code, msg, null);
    }

    public static ResponseResult err(String messages) {
        return new ResponseResult(ERROR, messages, null);
    }
}
