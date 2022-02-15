package com.poc.sqs.producer.configuration.amazonsqs;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.config.SimpleMessageListenerContainerFactory;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class LocalAWSSQSConfiguration {

    private static final String REGION = "eu-central-1";

    @Bean
    public QueueMessagingTemplate queueMessagingTemplate() {
        return new QueueMessagingTemplate(getAmazonSQSAsync());
    }

    @Bean
    @Primary
    public AmazonSQSAsync getAmazonSQSAsync() {
        final var serviceEndpoint = "http://localhost:4566";

        return AmazonSQSAsyncClientBuilder
                .standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(serviceEndpoint, REGION))
                .withCredentials(getAWSCredentialsProvider())
                .build();
    }

    @Bean
    public SimpleMessageListenerContainerFactory simpleMessageListenerContainerFactory(final AmazonSQSAsync amazonSQSAsyncClient) {
        final var factory = new SimpleMessageListenerContainerFactory();
        factory.setAmazonSqs(amazonSQSAsyncClient);
        factory.setMaxNumberOfMessages(1);
        return factory;
    }

    @Bean
    @Primary
    public AWSCredentialsProvider getAWSCredentialsProvider() {
        final BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(
                "test", "test");
        return new AWSStaticCredentialsProvider(basicAWSCredentials);
    }

}
