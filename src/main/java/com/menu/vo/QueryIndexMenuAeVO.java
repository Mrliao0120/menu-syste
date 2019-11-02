package com.menu.vo;

import com.menu.bean.MenuAe;
import com.menu.bean.MenuEvaluate;
import lombok.Data;

import java.util.List;

/**
 * @ProjectName menu-syste
 * @Author LHB
 * @Data 2019/10/31 16:13
 * @Version 1.0
 * @Description
 */
@Data
public class QueryIndexMenuAeVO extends MenuAe {

    List<QueryMenuEvaluateDetailVO> queryMenuEvaluateDetailVOS;

    //用户评论
    MenuEvaluate menuEvaluate;


}
