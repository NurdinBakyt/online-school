package org.school.authorization.controller;

import org.school.news.dto.MessageDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StartController {
    @GetMapping
    public MessageDto getMessage() {
        MessageDto messageDto = new MessageDto();
        messageDto.setMessage("Hello world!");
        return messageDto;
    }
}
