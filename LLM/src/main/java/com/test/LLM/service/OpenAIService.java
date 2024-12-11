package com.test.LLM.service;

import java.io.IOException;
import java.util.List;

import com.test.LLM.filter.UsersFilter;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.LLM.pojo.OpenAIRequest;
import com.test.LLM.pojo.OpenAIResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OpenAIService {

    private final WebClient webClient;
    private final ObjectMapper objectMapper;
    private final UsersFilter usersFilter;

    public OpenAIService(WebClient webClient, ObjectMapper objectMapper, UsersFilter usersFilter) {
        this.webClient = webClient;
        this.objectMapper = objectMapper;
        this.usersFilter = usersFilter;
    }

    public Flux<String> getStreamedResponse(String userMessage, String ipAddress) {

        // 获取消息列表
        List<OpenAIRequest.Message> messages = usersFilter.getUsersMap().get(ipAddress);
        messages.add(new OpenAIRequest.Message("user", userMessage));
        
        OpenAIRequest request = new OpenAIRequest();
        request.setModel("deepseek-chat")// 指定模型
               .setMessages(messages)
               .setStream(true); // 启用流式响应

        // 累积完整消息
        StringBuilder fullMsg = new StringBuilder();

        return webClient.post()
                .uri("/chat/completions")
                .bodyValue(request)
                .retrieve()
                .bodyToFlux(String.class)
                .concatMap(responseStr -> {
                    if ("[DONE]".equals(responseStr)) {
                        // 如果是 [DONE]，则不进行解析，触发完成逻辑
                        messages.add(new OpenAIRequest.Message("assistant", fullMsg.toString()));
                        System.out.println(messages);
                        return Mono.empty();
                    } else {
                        // 尝试将非 [DONE] 的字符串解析为 OpenAIResponse
                        try {
                            OpenAIResponse response = objectMapper.readValue(responseStr, OpenAIResponse.class);
                            String content = response.getChoices().get(0).getDelta().getContent();
                            fullMsg.append(content);
                            return Mono.just(content);
                        } catch (IOException e) {
                            // 处理解析错误
                            return Mono.error(e);
                        }
                    }
                });
                

                
    }
}
