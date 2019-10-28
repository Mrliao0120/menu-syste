package com.menu.util;



import lombok.Data;


@Data
public class AbstractInfoRequest<T> {


    /**版本**/
    private String  version;
    /**设备类型 1 Android 2.IOS 3 PE**/
    private String   facilitiesType;
    /**token*/
    private String token;
    /**请求值**/
    T   data;

    public AbstractInfoRequest(){
        AbstractInfoRequest abstractInfoRequest = AbstractRequestUtils.get();
        if (abstractInfoRequest!=null){
            this.token=abstractInfoRequest.getToken();
            this.facilitiesType=abstractInfoRequest.getFacilitiesType();
            this.version=abstractInfoRequest.getVersion();
        }

    }


}
