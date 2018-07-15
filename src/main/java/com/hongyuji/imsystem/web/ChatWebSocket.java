package com.hongyuji.imsystem.web;


import com.fasterxml.jackson.databind.util.BeanUtil;
import com.hongyuji.imsystem.domain.ChatMessage;
import com.hongyuji.imsystem.domain.FriendsMessage;
import com.hongyuji.imsystem.domain.Message;
import com.hongyuji.imsystem.util.JacksonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;


import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/websocket/{uid}")
@Component
public class ChatWebSocket {

    private Logger LOGGER = LoggerFactory.getLogger(ChatWebSocket.class);
    /**
     * 用来记录当前在线连接数
     */
    private static volatile int onlineCount = 0;

    private Long uid = null;

    /**
     * 用来存放每个客户端对应的MyWebSocket对象
     */
    private static ConcurrentHashMap<Long, ChatWebSocket> webSocketSet = new ConcurrentHashMap<>();


    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(@PathParam("uid") Long userId, Session session) {
        this.session = session;
        uid = userId;
        // 加入set中
        webSocketSet.put(userId,this);

        Set<Long> onlineUids = webSocketSet.keySet();
        FriendsMessage friendsMessage = new FriendsMessage();
        friendsMessage.setOnlineUid(new ArrayList<>(onlineUids));
        friendsMessage.setOfflineUid(new ArrayList<>());

        Message<FriendsMessage> msg = Message.buildSuccessMessage(friendsMessage);
        String msgStr = JacksonUtils.toJson(msg);
        webSocketSet.forEach((key,value)-> webSocketSet.get(key).sendMessage(msgStr));
        // 在线数加1
        addOnlineCount();
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        // 从set中删除
        webSocketSet.remove(uid);
        // 在线数减1
        subOnlineCount();
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
        ChatMessage chatMessage = JacksonUtils.toObject(message,ChatMessage.class);
        Long receiver = chatMessage.getReceiver();
        if(webSocketSet.containsKey(receiver)){
            Message<ChatMessage> msg = Message.buildSuccessMessage(chatMessage);
            webSocketSet.get(receiver).sendMessage(JacksonUtils.toJson(msg));
        }

    }

    /**
     * 发生错误时调用
     *
     * @OnError
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }


    private void sendMessage(String message) {
        try {
            this.session.getBasicRemote().sendText(message);
            //this.session.getAsyncRemote().sendText(message);
        }catch (Exception e){
            System.out.println("send message error. message = " + message);
        }

    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        ChatWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        ChatWebSocket.onlineCount--;
    }

}
