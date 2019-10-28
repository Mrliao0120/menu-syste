package com.menu.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AccountUser implements Serializable {
    private Long id;

    /**用户账号**/
    private String username;
    /**用户密码**/
    private String password;
    /**用户昵称**/
    private String nickname;
    /**是否删除 0否 1是**/
    private Integer isDelete;
    /**是否锁定  0否 1是**/
    private Integer isLock;
    /**创建时间**/
    private Date gmtCreate;
    /**更新时间**/
    private Date gmtModified;
    /**系统权限等级  1.root 2.管理员 3菜品管理员**/
    private Integer systemLevel;

    private static final long serialVersionUID = 1L;


}