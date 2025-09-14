package com.llm.spring_ai.Controller;

import com.llm.spring_ai.Service.ChatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/chat")
    public Flux<String> Chat(@RequestParam("chat_id") String chatId, String prompt) {
        return chatService.Chat(chatId, prompt);
    }

    @GetMapping("/clear")
    public Mono<String> clearMemory(@RequestParam("chat_id") String chatId) {
        return chatService.clearMemory(chatId);
    }

}
