package cn.beichenhpy.springwebfluxdemo.service.impl;

import cn.beichenhpy.springwebfluxdemo.domain.User;
import cn.beichenhpy.springwebfluxdemo.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import reactor.core.publisher.Mono;

@SpringBootTest
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    User user = new User();

    @BeforeEach
    void setUp() {
        user.setId("test");
        user.setName("test");
        user.setAge(1);
    }

    @Test
    void getUserById() {
        String userId = null;
        Mockito.when(userRepository.getUserById(userId)).thenReturn(Mono.empty());
        Mono<User> nullUser = userService.getUserById(null);
        Assertions.assertNotNull(nullUser);
        Assertions.assertNull(nullUser.block());

        userId = "test";
        Mockito.when(userRepository.getUserById(userId)).thenReturn(Mono.just(user));
        Mono<User> testUser = userService.getUserById("test");
        Assertions.assertNotNull(testUser);
        User user = testUser.block();
        Assertions.assertNotNull(user);
        Assertions.assertEquals(user.getId(), "test");
    }

    @Test
    void addUser() {
        //mock repository
        Mockito.when(userRepository.addUser(user)).thenReturn(Mono.just(true));
        Mono<String> id = userService.addUser(user);
        Assertions.assertNotNull(id);
        Assertions.assertEquals(id.block(), "test");

        Mockito.when(userRepository.addUser(null)).thenReturn(Mono.just(true));
        Mono<String> userMono = userService.addUser(null);
        Assertions.assertNotNull(userMono);
        Assertions.assertNull(userMono.block());

        user.setId(null);
        Mockito.when(userRepository.addUser(user)).thenReturn(Mono.just(true));
        Mono<String> idNullUserMono = userService.addUser(user);
        Assertions.assertNotNull(idNullUserMono);
        Assertions.assertNotNull(idNullUserMono.block());
    }
}