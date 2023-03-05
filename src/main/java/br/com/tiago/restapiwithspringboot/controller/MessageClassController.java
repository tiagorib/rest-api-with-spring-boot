package br.com.tiago.restapiwithspringboot.controller;

import br.com.tiago.restapiwithspringboot.entity.MessageClass;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MessageClassController {

    private static final String template = "Hello, %s!";
    private static final AtomicLong counter = new AtomicLong();

    @RequestMapping("messages")
    public MessageClass messageClass(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new MessageClass(counter.incrementAndGet(), String.format(template, name));
    }

}
