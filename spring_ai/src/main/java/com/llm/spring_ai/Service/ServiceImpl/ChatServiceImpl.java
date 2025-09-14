package com.llm.spring_ai.Service.ServiceImpl;

import com.llm.spring_ai.Service.ChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ChatServiceImpl implements ChatService {
    private final ChatClient chatClient;
    private final ChatMemory chatMemory;

    public ChatServiceImpl(ChatClient chatClient, ChatMemory chatMemory) {
        this.chatClient = chatClient;
        this.chatMemory = chatMemory;
    }

    @Override
    public Flux<String> Chat(String chatId, String prompt) {
        return chatClient.prompt()
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, chatId))
                .user(prompt)
                .stream()
                .content();
    }

    @Override
    public Mono<String> clearMemory(String chatId) {
        chatMemory.clear(chatId);
        return Mono.just("Chat \"" + chatId + "\" has been cleared");
    }
}
