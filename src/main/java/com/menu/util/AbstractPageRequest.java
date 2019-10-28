package com.menu.util;

import lombok.Data;

@Data
public class AbstractPageRequest<T> extends AbstractInfoRequest{
        /**分页条数**/
        private Integer  pageSize=10;
        /**当前页数**/
        private Integer currPage=1;
        /**排序字段**/
        private String  orderBy;
        public  AbstractPageRequest(){
                AbstractPageRequest abstractPageRequest = AbstractPageRequestUtils.get();
                if (abstractPageRequest!=null){
                       this.pageSize=abstractPageRequest.getPageSize();
                       this.currPage=abstractPageRequest.getCurrPage();
                }
        }
}
