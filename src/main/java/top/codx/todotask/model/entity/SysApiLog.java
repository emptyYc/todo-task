package top.codx.todotask.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统api日志表;
 *
 * @author : Liuch
 * @date : 2023-12-28
 */
@Data
@ApiModel(value = "系统api日志表", description = "系统api日志表")
public class SysApiLog implements Serializable {
    private static final long serialVersionUID = -961785178197268387L;
    /**
     * 调用id
     */
    @ApiModelProperty(name = "调用id", notes = "调用id")
    private String id;
    /**
     * 调用者
     */
    @ApiModelProperty(name = "调用者", notes = "调用者")
    private String userName;
    /**
     * 调用ip
     */
    @ApiModelProperty(name = "调用ip", notes = "调用ip")
    private String ip;
    /**
     * 请求路径
     */
    @ApiModelProperty(name = "请求路径", notes = "请求路径")
    private String url;
    /**
     * 操作title
     */
    @ApiModelProperty(name = "操作title", notes = "操作title")
    private String title;
    /**
     * 调用的方法
     */
    @ApiModelProperty(name = "调用的方法", notes = "调用的方法")
    private String method;
    /**
     * 请求方式
     */
    @ApiModelProperty(name = "请求方式", notes = "请求方式")
    private String requestType;
    /**
     * 业务类型
     */
    @ApiModelProperty(name = "业务类型", notes = "业务类型")
    private String type;
    /**
     * 请求参数
     */
    @ApiModelProperty(name = "请求参数", notes = "请求参数")
    private String params;
    /**
     * 返回结果
     */
    @ApiModelProperty(name = "返回结果", notes = "返回结果")
    private String result;
    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd hh:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @ApiModelProperty(name = "创建时间", notes = "创建时间")
    private Date createdTime;
    /**
     * 创建人
     */
    @ApiModelProperty(name = "创建人", notes = "创建人")
    private String createdBy;
    /**
     * 更新时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd hh:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @ApiModelProperty(name = "更新时间", notes = "更新时间")
    private Date updatedTime;
    /**
     * 更新人
     */
    @ApiModelProperty(name = "更新人", notes = "更新人")
    private String updatedBy;
}
