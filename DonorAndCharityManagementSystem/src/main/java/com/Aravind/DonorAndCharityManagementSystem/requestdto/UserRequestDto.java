package com.Aravind.DonorAndCharityManagementSystem.requestdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    private String userName;
    private String email;
    private String password;
    private String phoneNumber;
    private String role;
}
