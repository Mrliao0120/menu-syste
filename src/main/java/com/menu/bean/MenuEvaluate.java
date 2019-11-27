package com.menu.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(value = "新增菜品评价实体")
public class MenuEvaluate implements Serializable {
    private Long id;
    /**菜品评价**/
    @ApiModelProperty(value = "菜品评价")
    private String menuEvaluate;
    /**菜品分数**/
    @ApiModelProperty(value = "菜品分数")
    private Integer menuEvaluateScore;
    /**创建用户**/
    @ApiModelProperty(value = "创建用户")
    private Long userId;
    /**菜品评论父id**/
    @ApiModelProperty(value = "菜品评论父id")
    private Long menuEvaluateId;
    /**是否是管路员回复**/
    @ApiModelProperty(value = "是否是管路员回复")
    private Integer isRootReply;
    /**是否删除**/
    @ApiModelProperty(value = "是否删除")
    private Integer isDelete;
    /**创建时间**/
    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;
    /**修改时间**/
    @ApiModelProperty(value = "修改时间")
    private Date gmtModified;
    /**回复类型 1普通 2商家**/
    @ApiModelProperty(value = "回复类型 1普通 2商家")
    private Integer menuType;
    /**菜品ID**/
    @ApiModelProperty(value = "菜品ID")
    private Long menuId;

    private static final long serialVersionUID = 1L;


}