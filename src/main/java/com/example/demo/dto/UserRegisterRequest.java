package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRegisterRequest {
    @NotBlank(message = "User name không được để trống")
    String username;

    @NotBlank(message = "Email không được để trống")
    @Pattern(regexp = "^[A-Za-z0-9!#%&'*+/=?^_`{|}~-]+(?:\\.[A-Za-z0-9!#%&'*+/=?^_`{|}~-]+)*@(?:[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?\\.)+[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?$", message = "Cần nhập đúng định dạng email")
    String email;

    @NotBlank(message = "Mật khẩu không được để trống")
    String password;
}
