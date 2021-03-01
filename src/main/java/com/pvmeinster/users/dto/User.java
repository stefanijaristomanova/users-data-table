package com.pvmeinster.users.dto;

//import jdk.nashorn.internal.objects.annotations.Getter;
//import jdk.nashorn.internal.objects.annotations.Setter;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ApiModel(description = "Response body for user")
public class User implements Serializable {

    @NotNull
    private Long id;

    private String dateCreated;

    @NotNull
    private Integer deleted;

    @NotNull
    private String firstname;

    private String lastname;

    private String email;
}
