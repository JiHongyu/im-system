package com.hongyuji.imsystem.web;

import com.hongyuji.imsystem.domain.Response;
import com.hongyuji.imsystem.domain.request.PushChatRequest;
import com.hongyuji.imsystem.service.ChatService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/api/chat", method = {RequestMethod.GET,RequestMethod.POST})
public class ChatController {


    @Resource
    private ChatService chatService;


    @RequestMapping("/push")
    public Response pushChatContext(PushChatRequest request) throws Exception{

        chatService.recordChat(request.getSender(),
                request.getReceiver(),
                request.getContext());

        return Response.toSuccess();

    }




}
