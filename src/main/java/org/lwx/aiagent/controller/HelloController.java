package org.lwx.aiagent.controller;


import org.gitlab4j.api.GitLabApiException;
import org.lwx.aiagent.service.GitlabService;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    private ChatModel ollamaChatModel;

    @Autowired
    private GitlabService gitlabService;


    @GetMapping
    public String hello() {
        return "hello world";
    }

    @GetMapping(value = "/ollama/{value}")
    public String hello3(@PathVariable String value){
        return ollamaChatModel.call(new Prompt(value)).getResult().getOutput().getText();
    }

    //GitLab
    @GetMapping("/gitlab/projects")
    public String getProjects() throws GitLabApiException {
        return gitlabService.getProjects().toString();
    }

}
