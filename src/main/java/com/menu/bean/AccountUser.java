package com.menu.bean;

import com.menu.enums.SystemEnum;
import com.menu.exeception.ServletException;
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
    /**系统权限等级  1.管理员 2菜品管理员**/
    private Integer systemLevel;

    private static final long serialVersionUID = 1L;



    public void  checkParameter(AccountUser accountUser){
        if (accountUser==null||accountUser.getUsername()==null||accountUser.getPassword()==null
                ||accountUser.getUsername()==""||accountUser.getPassword()==""||accountUser.getSystemLevel()==null){
            throw new ServletException(SystemEnum.THE_PARAMETER_IS_INCORRECT.getCode(),
                    SystemEnum.THE_PARAMETER_IS_INCORRECT.getMsg());
        }

    }


    public void  checkLoginParameter(AccountUser accountUser){
        if (accountUser==null||accountUser.getUsername()==null||accountUser.getPassword()==null
                ||accountUser.getUsername()==""||accountUser.getPassword()==""){
            throw new ServletException(SystemEnum.THE_PARAMETER_IS_INCORRECT.getCode(),
                    SystemEnum.THE_PARAMETER_IS_INCORRECT.getMsg());
        }

    }

}