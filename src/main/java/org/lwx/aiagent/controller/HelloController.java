package org.lwx.aiagent.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    private ChatModel chatModel;

    @GetMapping
    public String hello() {
        return "hello world";
    }

    @GetMapping(value = "/{value}")
    public Flux<String> hello2(@PathVariable String value, HttpServletResponse response){
        // 避免返回乱码
        response.setCharacterEncoding("UTF-8");

        Flux<ChatResponse> stream = chatModel.stream(new Prompt(value));
        return stream.map(resp -> resp.getResult().getOutput().getContent());
    }
}
