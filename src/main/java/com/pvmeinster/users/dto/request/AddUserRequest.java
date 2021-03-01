package com.pvmeinster.users.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AddUserRequest implements Serializable {

    static final long serialVersionUID = 1L;

    @NotNull
    private String firstname;

    private String lastname;

    @NotNull
    private String email;

}