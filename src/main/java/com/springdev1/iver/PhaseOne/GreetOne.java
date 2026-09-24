package com.springdev1.iver.PhaseOne;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class GreetOne {

    private final MessageService messageService;

    public GreetOne(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/GreetOne")
    public String SendMessage() {
        return this.messageService.getMessage();
    }

}
