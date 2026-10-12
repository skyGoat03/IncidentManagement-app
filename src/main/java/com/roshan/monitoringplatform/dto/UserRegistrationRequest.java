package com.roshan.monitoringplatform.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationRequest {

    @NotNull
    public String email;

    @NotNull
    public  String password;


}
