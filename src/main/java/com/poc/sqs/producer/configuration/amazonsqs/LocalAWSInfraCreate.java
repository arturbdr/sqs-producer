package com.poc.sqs.producer.configuration.amazonsqs;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
@Slf4j
public class LocalAWSInfraCreate {

    private final AmazonSQSAsync amazonSQSAsync;
    private static final String DASHES = "-----------------------------------------------------";
    private static final String QUEUE_NAME = "payment-gateway-adyen-notifications";

    @PostConstruct
    public void instantiateServices() {
        logWarnMessage();

        // Create a new queue (locally)
        createQueue();
    }

    @SneakyThrows
    private void createQueue() {
        log.info("Creating queue {} locally", QUEUE_NAME);
        final var queueUrl = amazonSQSAsync
                .createQueueAsync(QUEUE_NAME)
                .get()
                .getQueueUrl();
        log.info("Queue {} created locally", queueUrl);
    }

    private void logWarnMessage() {
        log.warn(DASHES);
        log.warn(DASHES);
        log.warn("THIS CLASS SHOULD BE USED ONLY FOR LOCAL DEVELOPMENT!");
        log.warn(DASHES);
        log.warn(DASHES);
    }

}
