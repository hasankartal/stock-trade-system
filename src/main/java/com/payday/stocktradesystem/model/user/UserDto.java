package com.payday.stocktradesystem.model.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(description="All details about the userDto.")
public class UserDto {

    @Email(message = "Email should be valid")
    @ApiModelProperty(notes="Email which belongs to customer.")
    private String email;

    @NotNull(message = "Password cannot be null")
    @ApiModelProperty(notes="Password keeps customer's key.")
    private String password;

    @NotNull(message = "User name cannot be null")
    @ApiModelProperty(notes="Username keeps customer's name.")
    private String userName;
}