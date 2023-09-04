package cn.beichenhpy.springwebfluxdemo.repository;

import cn.beichenhpy.springwebfluxdemo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class UserRepository {

    @Autowired
    private ReactiveRedisTemplate<String, Object> reactiveRedisTemplate;


    public Mono<User> getUserById(String id) {
       return reactiveRedisTemplate.<String, User>opsForHash().get("USER:", id);
    }


    public Mono<Boolean> addUser(User user) {
       return reactiveRedisTemplate.<String, User>opsForHash().put("USER:", user.getId(), user);
    }
}
