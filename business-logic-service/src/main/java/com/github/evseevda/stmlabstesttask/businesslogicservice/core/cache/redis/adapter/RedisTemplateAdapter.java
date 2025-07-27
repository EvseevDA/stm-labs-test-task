package com.github.evseevda.stmlabstesttask.businesslogicservice.core.cache.redis.adapter;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class RedisTemplateAdapter {

    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;
    private final ExpirationConfig expirationConfig = new ExpirationConfig();

    public <T> void saveToList(String key, T value) {
        saveAll(key, List.of(value));
    }

    public <T> ExpirationConfig saveAll(String key, List<T> values) {
        List<String> jsonValues = values.stream()
                .map(this::serialize)
                .toList();
        redisTemplate.opsForList().rightPushAll(key, jsonValues.toArray(new String[0]));
        return expirationConfig;
    }

    public <T> Stream<T> findAll(String key, Class<T> requiredType) {
        List<String> jsonValues = redisTemplate.opsForList().range(key, 0, -1);
        return jsonValues != null ?
                jsonValues.stream().map(v -> deserialize(v, requiredType)) :
                Stream.empty();
    }

    public boolean existsByKey(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    @SneakyThrows
    private <T> String serialize(T value) {
        return objectMapper.writeValueAsString(value);
    }

    @SneakyThrows
    private <T> T deserialize(String json, Class<T> requiredType) {
        return objectMapper.readValue(json, requiredType);
    }

    public class ExpirationConfig {

        public void withExpiration(String key, Duration lifetime) {
            redisTemplate.expire(key, lifetime);
        }

    }

}
