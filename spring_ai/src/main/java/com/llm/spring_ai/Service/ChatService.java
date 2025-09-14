package com.llm.spring_ai.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ChatService {
    Flux<String> Chat(String chatId, String prompt);
    Mono<String> clearMemory(String chatId);
}
