package com.menu.vo;

import com.menu.util.AbstractPageRequest;
import lombok.Data;


/**
 * @ProjectName menu-syste
 * @Author LHB
 * @Data 2019/10/30 09:16
 * @Version 1.0
 * @Description
 */
@Data
public class QueryAccountUserRequest extends AbstractPageRequest {

    //昵称
    private String  search;



}
