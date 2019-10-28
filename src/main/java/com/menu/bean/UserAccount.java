package com.menu.bean;

import com.menu.enums.SystemEnum;
import com.menu.exeception.ServletException;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserAccount implements Serializable {
    private Long id;
    /**用户账号**/
    private String username;
    /**密码**/
    private String password;
    /**昵称**/
    private String nickname;
    /**是否删除**/
    private Integer isDelete;
    /**是否锁定 0否 1是**/
    private Integer isLock;
    /****/
    private Date gmtCreate;
    /****/
    private Date gmtModified;

    private static final long serialVersionUID = 1L;


    public void  checkInsertParameter(UserAccount userAccount){
        if (userAccount==null||userAccount.getUsername()==null||userAccount.getPassword()==null
                ||userAccount.getUsername()==""||userAccount.getPassword()==""
                ||userAccount.getNickname()==""||userAccount.getNickname()==null){
            throw new ServletException(SystemEnum.THE_PARAMETER_IS_INCORRECT.getCode(),
                    SystemEnum.THE_PARAMETER_IS_INCORRECT.getMsg());
        }
    }

    public void  checkLoginParameter(UserAccount userAccount){
        if (userAccount==null||userAccount.getUsername()==null||userAccount.getPassword()==null
                ||userAccount.getUsername()==""||userAccount.getPassword()==""){
            throw new ServletException(SystemEnum.THE_PARAMETER_IS_INCORRECT.getCode(),
                    SystemEnum.THE_PARAMETER_IS_INCORRECT.getMsg());
        }
    }
}