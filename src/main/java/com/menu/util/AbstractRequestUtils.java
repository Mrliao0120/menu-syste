package com.menu.util;

/**
 * @ProjectName movies_box
 * @Author
 * @Data 2019/5/23 15:35
 * @Version 1.0
 * @Description
 */
public class AbstractRequestUtils {

    //单例
    private AbstractRequestUtils(){

    }

    private static final ThreadLocal<AbstractInfoRequest>  REQUEST_INFO=new ThreadLocal<AbstractInfoRequest>();

    /**
     * set值
     * @param abstractInfoRequest
     */
    public static void set(AbstractInfoRequest abstractInfoRequest) {
        remove();
        REQUEST_INFO.set(abstractInfoRequest);
    }



    public static AbstractInfoRequest get(){
        AbstractInfoRequest abstractInfoRequest = REQUEST_INFO.get();
        remove();
        return abstractInfoRequest;
    }


    public static String  getToken(){
        return REQUEST_INFO.get().getToken();
    }

    public static String  getFacilitiesType(){
        return REQUEST_INFO.get().getFacilitiesType();
    }

    public static String  getVersion(){
        return REQUEST_INFO.get().getVersion();
    }

    public static void  remove(){
      REQUEST_INFO.remove();
    }



}
