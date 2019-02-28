package api.linguo.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import api.linguo.sockets.pojo.MessagePojo;
//import api.linguo.models.User;

@Controller
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/chat/get")
    public void message(String message) {
        this.template.convertAndSend("/chat", message);
    }

}