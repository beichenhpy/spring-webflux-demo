package cn.beichenhpy.springwebfluxdemo.service;


import cn.beichenhpy.springwebfluxdemo.domain.User;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<User> getUserById(String userId);

    Mono<String> addUser(User user);
}
