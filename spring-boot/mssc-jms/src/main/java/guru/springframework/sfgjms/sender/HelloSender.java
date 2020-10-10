package guru.springframework.sfgjms.sender;

import guru.springframework.sfgjms.config.JMSConfig;
import guru.springframework.sfgjms.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;


@RequiredArgsConstructor
@Component
public class HelloSender {

    private final JmsTemplate jmsTemplate;

    @Scheduled(fixedDelay = 2000)
    public void sendMessage() {

        System.out.println("I'm sending a message");

        HelloWorldMessage message = HelloWorldMessage
                .builder()
                .id(UUID.randomUUID())
                .message("Hello world")
                .build();

        jmsTemplate.convertAndSend(JMSConfig.MY_QUEUE,message);

        System.out.println("Sent");
    }
}
