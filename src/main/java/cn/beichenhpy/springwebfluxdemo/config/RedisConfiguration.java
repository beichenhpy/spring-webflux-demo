package cn.beichenhpy.springwebfluxdemo.config;


import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfiguration {

    @Bean
    public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory() {
        return new LettuceConnectionFactory();
    }

    @Bean
    public ReactiveRedisTemplate<String, Object> reactiveRedisTemplate(ReactiveRedisConnectionFactory reactiveRedisConnectionFactory) {
        RedisSerializationContext.RedisSerializationContextBuilder<String, Object> serializationContextBuilder = RedisSerializationContext.newSerializationContext();
        StringRedisSerializer stringSerializer = new StringRedisSerializer();
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        RedisSerializationContext<String, Object> context = serializationContextBuilder.key(stringSerializer)
                .value(genericJackson2JsonRedisSerializer)
                .hashKey(stringSerializer)
                .hashValue(genericJackson2JsonRedisSerializer)
                .build();
        return new ReactiveRedisTemplate<>(reactiveRedisConnectionFactory, context);
    }

}
