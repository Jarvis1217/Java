package com.llm.spring_ai.Config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {
    private final ChatClient.Builder chatClientBuilder;
    private final ChatMemory chatMemory;

    public ChatClientConfig(ChatClient.Builder chatClientBuilder, ChatMemory chatMemory) {
        this.chatClientBuilder = chatClientBuilder;
        this.chatMemory = chatMemory;
    }

    @Bean
    public ChatClient chatClient() {
        return chatClientBuilder
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .build();
    }
}
