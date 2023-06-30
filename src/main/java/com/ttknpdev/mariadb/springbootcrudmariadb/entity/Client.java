package com.ttknpdev.mariadb.springbootcrudmariadb.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor

@Entity
@Table(name = "client")
public class Client {
    @GeneratedValue(strategy = GenerationType.IDENTITY) // set prop to use for auto_increment
    //@Setter(AccessLevel.PRIVATE)
    private Long identity;
    @Id // set primary key
    @NotBlank(message = "(phone type string) shouldn't be empty")
    @Size(max = 10)
    private String phone;
    @NotBlank(message = "(fullname type string) shouldn't be empty")
    private String fullname;
    @NotNull(message = "(salary type float) should be more than 10000") // @NotNull for number
    @Min(10000)
    private Float salary;
    @NotNull
    private Boolean state;
    // these properties same fields on my tables

}
