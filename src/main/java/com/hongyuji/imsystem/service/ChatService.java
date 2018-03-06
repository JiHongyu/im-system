package com.hongyuji.imsystem.service;

import com.hongyuji.imsystem.dao.ChatDetailMapper;
import com.hongyuji.imsystem.domain.ChatDetail;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ChatService {

    @Resource
    private ChatDetailMapper chatDetailMapper;



    public void recordChat(long fromUid, long toUid, String context){
        ChatDetail chatDetail = new ChatDetail();
        chatDetail.setSender(fromUid);
        chatDetail.setReceiver(toUid);
        chatDetail.setContext(context);
        chatDetailMapper.insert(chatDetail);
    }
}
