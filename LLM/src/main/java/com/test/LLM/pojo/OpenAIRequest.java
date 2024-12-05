package com.test.LLM.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class OpenAIRequest {
    private String model;
    private List<Message> messages;
    private boolean stream;

    @Data
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class Message {
        private String role;
        private String content;
    }
}
