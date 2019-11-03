package com.menu.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.menu.bean.Message;
import com.menu.bean.UserAccount;
import com.menu.enums.SystemEnum;
import com.menu.exeception.ServletException;
import com.menu.util.UserAccountUtils;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author lhb
 * @Title:
 * @ProjectName menu-syste
 * @Description: TODO
 * @Date create in 9:15 2019/11/3
 */
@ServerEndpoint(value = "/websocket")
@Component
public class WebSocketService {
    /**
     * 全部在线会话  PS: 基于场景考虑 这里使用线程安全的Map存储会话对象。
     */
    private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();


    /**
     * 当客户端打开连接：1.添加会话对象 2.更新在线人数
     */
    @OnOpen
    public void onOpen(Session session) {
        onlineSessions.put(session.getId(), session);
        sendMessageToAll(Message.jsonStr(Message.ENTER, "", "", onlineSessions.size()));
    }

    /**
     * 当客户端发送消息：1.获取它的用户名和消息 2.发送消息给所有人
     * <p>
     * PS: 这里约定传递的消息为JSON字符串 方便传递更多参数！
     */
    @OnMessage
    public void onMessage(Session session, String jsonStr) {
        String data = JSON.toJSON(jsonStr).toString();
        JSONObject jsonObject = JSONObject.parseObject(data);
        Message message = new Message(Message.SPEAK,jsonObject.getString("username"),jsonObject.getString("msg"),onlineSessions.size());
        sendMessageToAll(Message.jsonStr(Message.SPEAK, message.getUsername(), message.getMsg(), onlineSessions.size()));
    }

    /**
     * 当关闭连接：1.移除会话对象 2.更新在线人数
     */
    @OnClose
    public void onClose(Session session) {
        onlineSessions.remove(session.getId());
        sendMessageToAll(Message.jsonStr(Message.QUIT, "", "下线了！", onlineSessions.size()));
    }

    /**
     * 当通信发生异常：打印错误日志
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    /**
     * 公共方法：发送信息给所有人
     */
    private static void sendMessageToAll(String msg) {
        onlineSessions.forEach((id, session) -> {
            try {
                session.getBasicRemote().sendText(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }



}
