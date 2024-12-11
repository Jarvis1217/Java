package com.test.LLM.controller;

import com.test.LLM.filter.UsersFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.test.LLM.service.OpenAIService;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;

@RestController
public class OpenAIController {

    private final OpenAIService openAIService;
    private final UsersFilter usersFilter;

    public OpenAIController(OpenAIService openAIService, UsersFilter usersFilter) {
        this.openAIService = openAIService;
        this.usersFilter = usersFilter;
    }

    @GetMapping(value = "/chat", produces = "text/event-stream;charset=UTF-8")
    public Flux<String> chat(@RequestParam String message, ServerWebExchange exchange) {
        String ipAddress = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
        return openAIService.getStreamedResponse(message, ipAddress);
    }

    @GetMapping(value = "/clear")
    public String clear(ServerWebExchange exchange) {
        String ipAddress = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
        usersFilter.getUsersMap().remove(ipAddress);
        return "Done";
    }
}
