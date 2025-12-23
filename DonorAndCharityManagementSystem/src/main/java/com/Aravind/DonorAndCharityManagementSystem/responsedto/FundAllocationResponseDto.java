package com.Aravind.DonorAndCharityManagementSystem.responsedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FundAllocationResponseDto {
    private Long id;
    private Double amountUsed;
    private String usageDescription;
    private String usedDate;
    private Long organizationId;
    private Long campaignId;
}
