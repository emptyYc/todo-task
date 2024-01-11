package top.codx.todotask.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


/**
 * 用户表;
 *
 * @author : Liuch
 * @date : 2023-12-28
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "用户表", description = "用户表")
public class SysUser implements Serializable {
    private static final long serialVersionUID = -8478668446629391204L;
    /**
     * 主键
     */
    @ApiModelProperty(name = "主键", notes = "主键")
    private String id;
    /**
     * 用户编码
     */
    @ApiModelProperty(name = "用户编码", notes = "用户编码")
    private String userCode;
    /**
     * 用户实名
     */
    @ApiModelProperty(name = "用户实名", notes = "用户实名")
    private String userName;
    /**
     * 昵称
     */
    @ApiModelProperty(name = "昵称", notes = "昵称")
    private String nickName;
    /**
     * 密码
     */
    @ApiModelProperty(name = "密码", notes = "密码")
    private String userPasswd;
    /**
     * 用户性别
     */
    @ApiModelProperty(name = "用户性别", notes = "用户性别")
    private String userSex;
    /**
     * 角色ID
     */
    @ApiModelProperty(name = "角色ID", notes = "角色ID")
    private String roleId;
    /**
     * 用户头像
     */
    @ApiModelProperty(name = "用户头像", notes = "用户头像")
    private String userAvatar;
    /**
     * 用户手机号
     */
    @ApiModelProperty(name = "用户手机号", notes = "用户手机号")
    private String userPhone;
    /**
     * 用户邮箱
     */
    @ApiModelProperty(name = "用户邮箱", notes = "用户邮箱")
    private String userEmail;
    /**
     * 用户身份认证（0未认证，1已认证）
     */
    @ApiModelProperty(name = "用户身份认证（0未认证，1已认证）", notes = "用户身份认证（0未认证，1已认证）")
    private String userAutonym;
    /**
     * 用户地址
     */
    @ApiModelProperty(name = "用户地址", notes = "用户地址")
    private String userSite;
    /**
     * 用户出生日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd hh:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @ApiModelProperty(name = "用户出生日期", notes = "用户出生日期")
    private Date userBirthday;
    /**
     * 绑定微信号
     */
    @ApiModelProperty(name = "绑定微信号", notes = "绑定微信号")
    private String wxOpenid;
    /**
     * 用户个性签名
     */
    @ApiModelProperty(name = "用户个性签名", notes = "用户个性签名")
    private String userSignature;
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
    /**
     * 是否删除（0否 1是）
     */
    @ApiModelProperty(name = "是否删除（0否 1是）", notes = "是否删除（0否 1是）")
    private String isDel;
    /**
     * 是否启用（0禁用 1启用）
     */
    @ApiModelProperty(name = "是否启用（0禁用 1启用）", notes = "是否启用（0禁用 1启用）")
    private String isEnable;
    /**
     * 备注
     */
    @ApiModelProperty(name = "备注", notes = "备注")
    private String remarks;
}