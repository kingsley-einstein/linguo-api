package api.linguo.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import api.linguo.sockets.pojo.MessagePojo;
//import api.linguo.models.User;

@Controller
public class WebSocketController {

    @MessageMapping("/message")
    @SendTo("/chat/get")
    public MessagePojo message(String message) {
        return new MessagePojo(message);
    }

}