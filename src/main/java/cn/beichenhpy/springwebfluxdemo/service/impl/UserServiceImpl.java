package cn.beichenhpy.springwebfluxdemo.service.impl;

import cn.beichenhpy.springwebfluxdemo.domain.User;
import cn.beichenhpy.springwebfluxdemo.repository.UserRepository;
import cn.beichenhpy.springwebfluxdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<User> getUserById(String userId) {
        return userRepository.getUserById(userId);
    }

    @Override
    public Mono<String> addUser(User user) {
        if (user == null) {
            return Mono.empty();
        }
        if (!StringUtils.hasText(user.getId())) {
            user.setId(UUID.randomUUID().toString());
        }
        return userRepository.addUser(user)
                .map(result -> result ? user.getId() : null);
    }
}
