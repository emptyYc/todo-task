package top.codx.todotask.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 任务信息表;
 *
 * @author : Liuch
 * @date : 2023-12-28
 */
@Data
@ApiModel(value = "任务信息表", description = "任务信息表")
public class TaskInfo implements Serializable {
    private static final long serialVersionUID = -5610738605732678515L;
    /**
     * 主键ID
     */
    @ApiModelProperty(name = "主键ID", notes = "主键ID")
    private String id;
    /**
     * 用户ID
     */
    @ApiModelProperty(name = "用户ID", notes = "用户ID")
    private String userId;
    /**
     * 用户编码
     */
    @ApiModelProperty(name = "用户编码", notes = "用户编码")
    private String userCode;
    /**
     * 用户名称
     */
    @ApiModelProperty(name = "用户名称", notes = "用户名称")
    private String userName;
    /**
     * 点赞数
     */
    @ApiModelProperty(name = "点赞数", notes = "点赞数")
    private String likeNum;
    /**
     * 内容
     */
    @ApiModelProperty(name = "内容", notes = "内容")
    private String content;
    /**
     * 创建人
     */
    @ApiModelProperty(name = "创建人", notes = "创建人")
    private String createdBy;
    /**
     * 创建时间
     */
    @ApiModelProperty(name = "创建时间", notes = "创建时间")
    private Date createdTime;
    /**
     * 更新人
     */
    @ApiModelProperty(name = "更新人", notes = "更新人")
    private String updatedBy;
    /**
     * 更新时间
     */
    @ApiModelProperty(name = "更新时间", notes = "更新时间")
    private Date updatedTime;
    /**
     * 是否完成
     */
    @ApiModelProperty(name = "是否完成", notes = "是否完成")
    private String isCompleted;
    /**
     * 是否删除
     */
    @ApiModelProperty(name = "是否删除", notes = "是否删除")
    private String isDel;
    /**
     * 备注信息
     */
    @ApiModelProperty(name = "备注信息", notes = "备注信息")
    private String remarks;
}