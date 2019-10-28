package com.menu.util;

import lombok.Data;

import java.util.List;

@Data
public class AbstractPageResponse<T> extends  AbstractInfoRequest{


    /*** 分页条数**/
    private Integer pageSize;
    /*** 当前页数**/
    private Integer currPage;
    /**总记录数**/
    private Integer totalCount;
    /**总页数**/
    private Integer totalPage;
    /*** 数据集合**/
    private List<T> dataList;



}
