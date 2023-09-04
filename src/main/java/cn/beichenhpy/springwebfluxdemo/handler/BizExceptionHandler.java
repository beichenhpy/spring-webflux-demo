package cn.beichenhpy.springwebfluxdemo.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class BizExceptionHandler {


    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ResponseEntity<List<FieldError>>> MethodArgumentNotValidExceptionHandler(WebExchangeBindException e) {
        e.printStackTrace();
        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getFieldErrors()));
    }

    @ExceptionHandler(RuntimeException.class)
    public Mono<ResponseEntity<?>> allExceptionHandler(RuntimeException e) {
        e.printStackTrace();
        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("inner error"));
    }
}
