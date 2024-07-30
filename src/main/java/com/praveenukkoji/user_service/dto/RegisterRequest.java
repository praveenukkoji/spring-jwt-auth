package com.praveenukkoji.user_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotEmpty(message = "fullname is empty")
    @NotBlank(message = "fullname is blank")
    private String fullname;

    @NotEmpty(message = "email is empty")
    @NotBlank(message = "email is blank")
    @Email(message = "email format is incorrect")
    private String email;

    @NotEmpty(message = "password is empty")
    @NotBlank(message = "password is blank")
    @Size(min = 8, message = "password should be minimum 8 characters")
    private String password;
}
