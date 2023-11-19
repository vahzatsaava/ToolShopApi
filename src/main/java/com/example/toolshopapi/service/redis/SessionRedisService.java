package com.example.toolshopapi.service.redis;

import com.example.toolshopapi.dto.auth.SignUpDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Slf4j
@Service
@RequiredArgsConstructor
public class SessionRedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    private static final Duration TOKEN_EXPIRATION = Duration.ofDays(30);


    public void saveUserSession(SignUpDto signUpDto) {
        try {
            String sessionKey = "user-session:" + signUpDto.getEmail();
            redisTemplate.opsForValue().set(sessionKey, signUpDto, TOKEN_EXPIRATION);
            log.info("User session saved for email: {}", signUpDto.getEmail());
        } catch (Exception e) {
            log.error("Error saving user session for email: {}", signUpDto.getEmail(), e);
        }
    }

    public Boolean checkUserCreation(SignUpDto signUpDto) {
        String sessionKey = "user-session:" + signUpDto.getEmail();

        SignUpDto cachedUser = (SignUpDto) redisTemplate.opsForValue().get(sessionKey);

        if (cachedUser != null) {
            log.info("User Name: {}", cachedUser.getEmail());
            return true;
        } else {
            log.info("User Name: {} not found", signUpDto.getEmail());
            return false;
        }
    }

    public Boolean deleteSession(SignUpDto signUpDto) {
        String sessionKey = "user-session:" + signUpDto.getEmail();
        return redisTemplate.delete(sessionKey);

    }
}
