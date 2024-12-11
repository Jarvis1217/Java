package com.test.LLM.filter;

import com.test.LLM.pojo.OpenAIRequest;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Component
public class UsersFilter implements WebFilter {
    private final ConcurrentHashMap<String, List<OpenAIRequest.Message>> usersMap = new ConcurrentHashMap<>();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String ipAddress = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();

        if (!usersMap.containsKey(ipAddress)) {
            List<OpenAIRequest.Message> messages = new ArrayList<>();
            messages.add(new OpenAIRequest.Message("system", ""));
            usersMap.put(ipAddress, messages);
        }

        return chain.filter(exchange);
    }
}
