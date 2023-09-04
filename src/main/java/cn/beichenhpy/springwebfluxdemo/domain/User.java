package cn.beichenhpy.springwebfluxdemo.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;

    @NotBlank(message = "name can not be blank")
    private String name;

    @Min(value = 1, message = "age cannot be zero")
    private Integer age;

}
