package com.poc.sqs.producer.gateway.controller;

import com.poc.sqs.producer.usecase.SendSimpleMessageImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sample")
@RequiredArgsConstructor
@Slf4j
public class SimpleController {

    private final SendSimpleMessageImpl sendSimpleMessage;

    @PostMapping()
    public ResponseEntity sendMessage(@RequestBody final String request) {
        log.info("Simple String  {}", request);
        sendSimpleMessage.execute(request);
        return ResponseEntity.ok().build();
    }
}
