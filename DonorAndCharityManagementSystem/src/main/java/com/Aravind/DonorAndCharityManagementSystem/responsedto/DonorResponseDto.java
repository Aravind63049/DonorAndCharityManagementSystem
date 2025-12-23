package com.Aravind.DonorAndCharityManagementSystem.responsedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonorResponseDto {
    private long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private long userId;
}
