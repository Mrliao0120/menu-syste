package com.menu.util;

import lombok.Data;

import java.io.Serializable;

/**
 * @ProjectName movies_box
 * @Author
 * @Data 2019/5/6 09:31
 * @Version 1.0
 * @Description
 */
@Data
public class ResultData<T> implements Serializable {

        private Integer code=200;

        private String msg="请求成功";

        private T   data;


}
