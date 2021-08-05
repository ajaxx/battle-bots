package org.battlebots.spring.config;

import org.battlebots.arena.SimulatorFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer  {
    private SimulatorFactory simulatorFactory;

    /**
     * Constructor
     * @param simulatorFactory the simulator factory to use with this configuration.
     */
    public WebSocketConfig(final SimulatorFactory simulatorFactory) {
        this.simulatorFactory = simulatorFactory;
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/arena").withSockJS();
    }
}