package com.Aravind.DonorAndCharityManagementSystem.requestdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonorRequestDto {
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private Long userId;
}