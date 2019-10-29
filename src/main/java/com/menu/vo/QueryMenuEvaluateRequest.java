package com.menu.vo;

import com.menu.util.AbstractPageRequest;
import lombok.Data;

/**
 * @author lhb
 * @Title:
 * @ProjectName menu-syste
 * @Description: TODO
 * @Date create in 20:39 2019/10/29
 */
@Data
public class QueryMenuEvaluateRequest extends AbstractPageRequest {

        //菜品名称
        private String menuName;

        private Long  createUserId;
}
