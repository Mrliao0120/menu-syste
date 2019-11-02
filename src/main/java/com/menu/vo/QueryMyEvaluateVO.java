package com.menu.vo;

import com.menu.bean.MenuEvaluate;
import lombok.Data;

/**
 * @author lhb
 * @Title:
 * @ProjectName menu-syste
 * @Description: TODO
 * @Date create in 19:47 2019/11/2
 */
@Data
public class QueryMyEvaluateVO  extends MenuEvaluate{

    //菜品ID
    private Long menuId;
    //菜品名称
    private String menuName;
    //菜品图片
    private String menuImage;





}
