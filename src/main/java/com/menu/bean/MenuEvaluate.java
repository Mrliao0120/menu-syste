package com.menu.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MenuEvaluate implements Serializable {
    private Long id;
    /**菜品评价**/
    private String menuEvaluate;
    /**菜品分数**/
    private Integer menuEvaluateScore;
    /**创建 用户**/
    private Integer userId;
    /**菜品评论父id**/
    private Long menuEvaluateId;
    /**是否是管路员回复**/
    private Integer isRootReply;
    /**是否删除**/
    private Integer isDelete;
    /**创建时间**/
    private Date gmtCreate;
    /**修改时间**/
    private Date gmtModified;
    /**回复类型 1普通 2商家**/
    private Integer menuType;

    private static final long serialVersionUID = 1L;

}