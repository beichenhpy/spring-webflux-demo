package cn.beichenhpy.springwebfluxdemo.controller;


import cn.beichenhpy.springwebfluxdemo.domain.User;
import cn.beichenhpy.springwebfluxdemo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequestMapping("/v1/user")
@RestController
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping
    public Mono<ResponseEntity<User>> getUser(@RequestParam String id) {
       return userService.getUserById(id)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    public Mono<ResponseEntity<String>> addUser(@RequestBody @Valid User user) {
        return userService.addUser(user)
                .map(ResponseEntity::ok);
    }

}
