package com.menu.bean;

import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * @author lhb
 * @Title:
 * @ProjectName menu-syste
 * @Description: TODO
 * @Date create in 10:19 2019/11/3
 */
@Data
public class Message {
    public static final String ENTER = "ENTER";
    public static final String SPEAK = "SPEAK";
    public static final String QUIT = "QUIT";

    private String type;//消息类型

    private String username; //发送人

    private String msg; //发送消息

    private int onlineCount; //在线用户数

    public static String jsonStr(String type, String username, String msg, int onlineTotal) {
        return JSON.toJSONString(new Message(type, username, msg, onlineTotal));
    }

    public Message(String type, String username, String msg, int onlineCount) {
        this.type = type;
        this.username = username;
        this.msg = msg;
        this.onlineCount = onlineCount;
    }

    //这里省略get/set方法 请自行补充
}
