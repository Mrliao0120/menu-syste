package com.menu.enums;



/**
 * @ProjectName movies_box
 * @Author LHB
 * @Data 2019/5/27 09:11
 * @Version 1.0
 * @Description
 */
public enum SystemEnum {
    ACCOUNT_EXCEPTION(3331,"账号异常"),
    ACCOUNT_ALREADY_EXISTS(3332,"账号已存在"),
    LOGIN_EXCEPTION(3333,"登录异常"),
    WRONG_PASSWORD(3334,"密码错误"),
    INCORRECT_ACCOUNT_OR_PASSWORD(3335,"账号或者密码错误"),
    ACCOUNT_ALREADY_NO_EXISTS(3332,"账号不存在"),
    THE_PARAMETER_IS_INCORRECT(4000,"必填信息为空"),
    PERMISSION_IS_TOO_LOW(4001,"权限过低"),
    THE_RESULT_IS_EMPTY(5000,"The result is empty"),
    SYSTEM_ERROR(9999,"系统未知异常");



    //code
    private Integer code;
    //msg
    private String msg;

     SystemEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 根据code获取SystemEnum
     * @param code
     * @return
     */
    public SystemEnum  getByCode(Integer code){
        SystemEnum[] values = SystemEnum.values();
        for (SystemEnum systemEnum:values) {
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
