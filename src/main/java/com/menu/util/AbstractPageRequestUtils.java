package com.menu.util;

/**
 * @ProjectName movies_box
 * @Author
 * @Data 2019/5/27 09:32
 * @Version 1.0
 * @Description  分页参数相拦截实体
 */
public class AbstractPageRequestUtils {

    private AbstractPageRequestUtils(){

    }

  private final static   ThreadLocal<AbstractPageRequest> pageThread=new ThreadLocal<>();


    /**
     * set value
     * @param abstractPageRequest
     */
    public static void set(AbstractPageRequest abstractPageRequest){
        pageThread.remove();
        pageThread.set(abstractPageRequest);
    }


    public static AbstractPageRequest get(){
        AbstractPageRequest abstractPageRequest = pageThread.get();
        pageThread.remove();
        return abstractPageRequest;
    }





}
