package com.menu.vo;

import com.menu.bean.MenuEvaluate;
import lombok.Data;

/**
 * @author lhb
 * @Title:
 * @ProjectName menu-syste
 * @Description: TODO
 * @Date create in 22:41 2019/10/29
 */
@Data
public class QueryMenuEvaluateDetailVO extends MenuEvaluate {


    //菜品名称
    private String menuName;
    //用户昵称
    private String nickName;
    //回复
     private QueryMenuEvaluateDetailVO  returnMenuEvaluate;
}
