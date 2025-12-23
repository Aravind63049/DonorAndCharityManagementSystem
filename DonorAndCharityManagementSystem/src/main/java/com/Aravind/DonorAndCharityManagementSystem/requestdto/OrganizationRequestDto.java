package com.Aravind.DonorAndCharityManagementSystem.requestdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationRequestDto {
    private String orgName;
    private String orgAddress;
    private String orgEmail;
}

