package api.linguo.sockets;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/chat");
        registry.setApplicationDestinationPrefixes("/linguo");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry
        .addEndpoint("/linguo-endpoint")
        .setAllowedOrigins("http://localhost:8100", "http://192.168.43.72:8100", "http://192.168.56.1:8100", "http://192.168.99.1:8100")
        .withSockJS();
    }
}