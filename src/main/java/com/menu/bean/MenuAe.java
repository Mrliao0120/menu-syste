package com.menu.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel(value = "实体名称")
public class MenuAe implements Serializable {
    private Long id;
    /**菜品名称**/
    @ApiModelProperty(value = "菜品名称")
    private String menuName;
    /**菜品单价**/
    @ApiModelProperty(value = "菜品名称")
    private BigDecimal menuPrice;
    /**菜品图片**/
    @ApiModelProperty(value = "菜品名称")
    private String menuImage;
    /**创建人用户id**/
    @ApiModelProperty(value = "菜品名称")
    private Long createUserId;
    /**是否删除 0否 1是**/
    @ApiModelProperty(value = "菜品名称")
    private Integer isDelete;
    /**创建时间**/
    @ApiModelProperty(value = "菜品名称")
    private Date gmtCreate;
    /**修改时间**/
    @ApiModelProperty(value = "菜品名称")
    private Date gmtModified;
    /**菜品详细**/
    @ApiModelProperty(value = "菜品名称")
    private String menuText;
    /**菜品楼层**/
    @ApiModelProperty(value = "菜品名称")
    private String menuFloor;
    /**菜品窗口**/
    @ApiModelProperty(value = "菜品名称")
    private String menuWindow;
    /**食堂**/
    @ApiModelProperty(value = "食堂")
    private String canteenName;
    /**评分**/
    @ApiModelProperty(value = "评分")
    private Integer menuEvaluateScore;
    @ApiModelProperty(value = "X坐标")
    private String xCoord;
    @ApiModelProperty(value = "Y坐标")
    private String yCoord;

    private static final long serialVersionUID = 1L;

}