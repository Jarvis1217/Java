package com.test.LLM.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenAIResponse {
    private List<Choice> choices;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Choice {
        private Delta delta;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Delta {
        private String content;
    }
}
