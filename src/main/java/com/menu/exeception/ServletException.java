package com.menu.exeception;

import lombok.Data;
import lombok.ToString;

/**
 * @ProjectName menu-system
 * @Author
 * @Data 2019/5/24 09:39
 * @Version 1.0
 * @Description
 */
@Data
@ToString
public class ServletException extends RuntimeException {

    public ServletException(Integer code, String msg){
        this.code=code;
        this.msg=msg;
    }

    /**
     * 异常code
     */
    private Integer  code;
    /**
     *异常消息
     */
    private String msg;
}
