DROP TABLE IF EXISTS TASK_INFO;
CREATE TABLE TASK_INFO(
    `id` VARCHAR(32) NOT NULL   COMMENT '主键ID' ,
    `user_id` VARCHAR(32)    COMMENT '用户ID' ,
    `user_code` VARCHAR(21)    COMMENT '用户编码' ,
    `user_name` VARCHAR(90)    COMMENT '用户名称' ,
    `content` TEXT    COMMENT '内容' ,
    `created_by` VARCHAR(32)    COMMENT '创建人' ,
    `created_time` DATETIME    COMMENT '创建时间' ,
    `updated_by` VARCHAR(32)    COMMENT '更新人' ,
    `updated_time` DATETIME    COMMENT '更新时间' ,
    `is_completed` VARCHAR(1)    COMMENT '是否完成' ,
    `is_del` VARCHAR(1) NOT NULL   COMMENT '是否删除' ,
    `remarks` VARCHAR(900)    COMMENT '备注信息' ,
    PRIMARY KEY (id)
)  COMMENT = '任务信息表';


DROP TABLE IF EXISTS SYS_MENU;
CREATE TABLE SYS_MENU(
    `id` VARCHAR(32) NOT NULL   COMMENT '主键' ,
    `name` VARCHAR(90) NOT NULL   COMMENT '菜单名' ,
    `paternal_id` VARCHAR(32)    COMMENT '菜单父id' ,
    `order_num` INT    COMMENT '显示顺序' ,
    `url` VARCHAR(255)    COMMENT '权限url' ,
    `permission` VARCHAR(50)    COMMENT '权限' ,
    `type` VARCHAR(1)    COMMENT '类型 M 菜单 B 按钮 P 权限' ,
    `icon` VARCHAR(255)    COMMENT '图标' ,
    `created_time` DATETIME NOT NULL   COMMENT '创建时间' ,
    `created_by` VARCHAR(90) NOT NULL   COMMENT '创建人' ,
    `updated_time` DATETIME    COMMENT '更新时间' ,
    `updated_by` VARCHAR(90)    COMMENT '更新人' ,
    `is_del` VARCHAR(1) NOT NULL  DEFAULT '0' COMMENT '是否删除（0否 1是）' ,
    `remarks` VARCHAR(900)    COMMENT '备注' ,
    PRIMARY KEY (id)
)  COMMENT = '权限表';


DROP TABLE IF EXISTS SYS_ROLE;
CREATE TABLE SYS_ROLE(
    `id` VARCHAR(32) NOT NULL   COMMENT '主键' ,
    `role_code` VARCHAR(21) NOT NULL   COMMENT '普通用户、管理员;普通用户为小程序端用户，管理员为管理端用户。' ,
    `description` VARCHAR(900) NOT NULL   COMMENT '角色描述' ,
    `created_time` DATETIME NOT NULL   COMMENT '创建时间' ,
    `created_by` VARCHAR(90) NOT NULL   COMMENT '创建人' ,
    `updated_time` DATETIME    COMMENT '更新时间' ,
    `updated_by` VARCHAR(90)    COMMENT '更新人' ,
    `is_del` VARCHAR(1) NOT NULL  DEFAULT '0' COMMENT '是否删除（0否 1是）' ,
    `is_enable` VARCHAR(1) NOT NULL  DEFAULT '1' COMMENT '是否启用（0禁用 1启用）' ,
    `remarks` VARCHAR(900)    COMMENT '备注' ,
    PRIMARY KEY (id)
)  COMMENT = '角色表';


DROP TABLE IF EXISTS SYS_ROLE_MENU;
CREATE TABLE SYS_ROLE_MENU(
    `id` VARCHAR(32) NOT NULL   COMMENT '主键' ,
    `role_id` VARCHAR(32) NOT NULL   COMMENT '角色ID' ,
    `menu_id` VARCHAR(32) NOT NULL   COMMENT '菜单权限ID' ,
    `created_time` DATETIME NOT NULL   COMMENT '创建时间' ,
    `created_by` VARCHAR(90) NOT NULL   COMMENT '创建人' ,
    `updated_time` DATETIME    COMMENT '更新时间' ,
    `updated_by` VARCHAR(90)    COMMENT '更新人' ,
    `is_del` VARCHAR(1) NOT NULL  DEFAULT '0' COMMENT '是否删除（0否 1是）' ,
    `remarks` VARCHAR(900)    COMMENT '备注' ,
    PRIMARY KEY (id)
)  COMMENT = '角色权限表';


DROP TABLE IF EXISTS SYS_USER;
CREATE TABLE SYS_USER(
    `id` VARCHAR(32) NOT NULL   COMMENT '主键' ,
    `user_code` VARCHAR(21) NOT NULL   COMMENT '用户编码' ,
    `user_name` VARCHAR(255)    COMMENT '用户实名' ,
    `nick_name` VARCHAR(255)    COMMENT '昵称' ,
    `user_passwd` VARCHAR(255) NOT NULL   COMMENT '密码' ,
    `user_sex` VARCHAR(1)    COMMENT '用户性别' ,
    `role_id` VARCHAR(21) NOT NULL   COMMENT '角色ID' ,
    `user_avatar` VARCHAR(255)    COMMENT '用户头像' ,
    `user_phone` VARCHAR(21)    COMMENT '用户手机号' ,
    `user_email` VARCHAR(255)    COMMENT '用户邮箱' ,
    `user_autonym` VARCHAR(1)    COMMENT '用户身份认证（0未认证，1已认证）' ,
    `user_site` VARCHAR(255)    COMMENT '用户地址' ,
    `user_birthday` DATETIME    COMMENT '用户出生日期' ,
    `wx_openid` VARCHAR(255)    COMMENT '绑定微信号' ,
    `user_signature` VARCHAR(255)    COMMENT '用户个性签名' ,
    `created_time` DATETIME NOT NULL   COMMENT '创建时间' ,
    `created_by` VARCHAR(90) NOT NULL   COMMENT '创建人' ,
    `updated_time` DATETIME    COMMENT '更新时间' ,
    `updated_by` VARCHAR(90)    COMMENT '更新人' ,
    `is_del` VARCHAR(1) NOT NULL  DEFAULT '0' COMMENT '是否删除（0否 1是）' ,
    `is_enable` VARCHAR(1) NOT NULL  DEFAULT '1' COMMENT '是否启用（0禁用 1启用）' ,
    `remarks` VARCHAR(900)    COMMENT '备注' ,
    PRIMARY KEY (id)
)  COMMENT = '用户表';


DROP TABLE IF EXISTS SYS_USER_ROLE;
CREATE TABLE SYS_USER_ROLE(
    `user_id` VARCHAR(32) NOT NULL   COMMENT '用户id' ,
    `role_id` VARCHAR(32) NOT NULL   COMMENT '角色id' ,
    `created_time` DATETIME NOT NULL   COMMENT '创建时间' ,
    `created_by` VARCHAR(90) NOT NULL   COMMENT '创建人' ,
    `updated_time` DATETIME    COMMENT '更新时间' ,
    `updated_by` VARCHAR(90)    COMMENT '更新人'
)  COMMENT = '用户角色表';

DROP TABLE IF EXISTS SYS_API_LOG;
CREATE TABLE SYS_API_LOG(
    `id` VARCHAR(32) NOT NULL   COMMENT '调用id' ,
    `user_name` VARCHAR(90)    COMMENT '调用者' ,
    `ip` VARCHAR(90)    COMMENT '调用ip' ,
    `url` VARCHAR(255)    COMMENT '请求路径' ,
    `title` VARCHAR(255)    COMMENT '操作title' ,
    `method` VARCHAR(255)    COMMENT '调用的方法' ,
    `request_type` VARCHAR(10)    COMMENT '请求方式' ,
    `type` VARCHAR(10)    COMMENT '业务类型' ,
    `params` VARCHAR(800)    COMMENT '请求参数' ,
    `result` VARCHAR(6000)    COMMENT '返回结果' ,
    `created_time` DATETIME    COMMENT '创建时间' ,
    `created_by` VARCHAR(90) NOT NULL   COMMENT '创建人' ,
    `updated_time` DATETIME    COMMENT '更新时间' ,
    `updated_by` VARCHAR(90)    COMMENT '更新人' ,
    PRIMARY KEY (id)
)  COMMENT = '系统api日志表';
