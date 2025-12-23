package com.Aravind.DonorAndCharityManagementSystem.responsedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationResponseDto {
    private long id;
    private String orgName;
    private String orgAddress;
    private String orgEmail;
}