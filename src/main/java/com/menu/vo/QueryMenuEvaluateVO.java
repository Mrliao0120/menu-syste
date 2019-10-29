package com.menu.vo;

import com.menu.bean.MenuEvaluate;
import lombok.Data;

/**
 * @author lhb
 * @Title:
 * @ProjectName menu-syste
 * @Description: TODO
 * @Date create in 20:36 2019/10/29
 */
@Data
public class QueryMenuEvaluateVO extends MenuEvaluate {

        //菜品Id
        private Long  menuId;
        //菜品名称
        private String menuName;


}
