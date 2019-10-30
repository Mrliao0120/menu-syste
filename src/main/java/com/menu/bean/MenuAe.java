package com.menu.bean;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class MenuAe implements Serializable {
    private Long id;
    /**菜品名称**/
    private String menuName;
    /**菜品单价**/
    private BigDecimal menuPrice;
    /**菜品图片**/
    private String menuImage;
    /**创建人用户id**/
    private Long createUserId;
    /**是否删除 0否 1是**/
    private Integer isDelete;
    /**创建时间**/
    private Date gmtCreate;
    /**修改时间**/
    private Date gmtModified;
    /**菜品详细**/
    private String menuText;
    /**菜品楼层**/
    private String menuFloor;
    /**菜品窗口**/
    private String menuWindow;
    /**食堂**/
    private String canteenName;
    /**评分**/
    private Integer menuEvaluateScore;

    private static final long serialVersionUID = 1L;

}