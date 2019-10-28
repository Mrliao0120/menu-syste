package com.menu.enums;

/**
 * @ProjectName movies_box
 * @Author LHB
 * @Data 2019/5/27 16:45
 * @Version 1.0
 * @Description
 */
public enum UserLockEnum {

    ACCOUNT_NOT_LOCKED(0,"账户未锁定"),
    ACCOUNT_LOCKED(1,"账户已锁定"),
    ;



    //code
    private Integer code;
    //msg
    private String msg;

    UserLockEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 根据code获取SystemEnum
     * @param code
     * @return
     */
    public UserLockEnum getByCode(Integer code){
        UserLockEnum[] values = UserLockEnum.values();
        for (UserLockEnum systemEnum:values) {
            if (systemEnum.code==code){
                return systemEnum;
            }
        }
        return null;
    }




    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
