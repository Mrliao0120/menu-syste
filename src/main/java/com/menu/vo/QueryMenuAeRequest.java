package com.menu.vo;

import com.menu.util.AbstractPageRequest;
import lombok.Data;

/**
 * @author lhb
 * @Title:
 * @ProjectName menu-syste
 * @Description: TODO
 * @Date create in 19:15 2019/10/28
 */
@Data
public class QueryMenuAeRequest extends AbstractPageRequest {


    /**
     * 菜品名称
      */
   private   String  menuName;


   private Long  createUserId;



}
