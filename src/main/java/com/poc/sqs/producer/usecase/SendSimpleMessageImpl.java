package com.poc.sqs.producer.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SendSimpleMessageImpl {

    private final QueueMessagingTemplate queueMessagingTemplate;

    public void execute(final String simpleMessage) {
        final Message<String> message = MessageBuilder
                .createMessage(simpleMessage, new MessageHeaders(getHeaders()));

        this.queueMessagingTemplate.send("payment-gateway-adyen-notifications", message);
    }

    private Map<String, Object> getHeaders() {
        return Collections.emptyMap();
    }

}
