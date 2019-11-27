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
 * @Date create in 19:15 2019/10/28
 */
@Data
@ApiModel(value = "查询菜单实体")
public class QueryMenuAeRequest extends AbstractPageRequest {


    /**
     * 菜品名称
      */
    @ApiModelProperty(value = "菜品名称")
   private   String  menuName;

    @ApiModelProperty(value = "创建用户id")
   private Long  createUserId;



}
