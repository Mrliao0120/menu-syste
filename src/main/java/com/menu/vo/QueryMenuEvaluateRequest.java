package com.menu.vo;

import com.menu.util.AbstractPageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lhb
 * @Title:
 * @ProjectName menu-syste
 * @Description: TODO
 * @Date create in 20:39 2019/10/29
 */
@Data
@ApiModel(value = "查询我的评价实体")
public class QueryMenuEvaluateRequest extends AbstractPageRequest {

        //菜品名称
    @ApiModelProperty(value = "菜品名称")
        private String menuName;
    @ApiModelProperty(value = "id")
       private Long id;
    @ApiModelProperty(value = "创建用户id")
        private Long  createUserId;
}
