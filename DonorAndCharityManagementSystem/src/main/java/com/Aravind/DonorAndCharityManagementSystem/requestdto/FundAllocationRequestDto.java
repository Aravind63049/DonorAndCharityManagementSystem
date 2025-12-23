package com.Aravind.DonorAndCharityManagementSystem.requestdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FundAllocationRequestDto {
    private Double amountUsed;
    private String usageDescription;
    private LocalDateTime usedDate;
    private Long organizationId;
    private Long campaignId;
}
