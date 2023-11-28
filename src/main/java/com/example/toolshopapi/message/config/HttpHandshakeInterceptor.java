package com.example.toolshopapi.message.config;

import com.example.toolshopapi.model.models.User;
import com.example.toolshopapi.service.impl.UserServiceImpl;
import com.example.toolshopapi.service.redis.JwtCacheService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class HttpHandshakeInterceptor implements HandshakeInterceptor {
    private final JwtCacheService cacheService;
    private final UserServiceImpl userService;
    @Override
    public boolean beforeHandshake(@NotNull ServerHttpRequest request, @NotNull ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        String token = request.getHeaders().getFirst("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            String actualToken = token.substring(7);
            User user = userService.getCurrentUser();
            String actualToken2 = cacheService.getCachedTokenForEmail(user.getEmail()).orElse(null);
            return actualToken.equals(actualToken2);
        }
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }
}
