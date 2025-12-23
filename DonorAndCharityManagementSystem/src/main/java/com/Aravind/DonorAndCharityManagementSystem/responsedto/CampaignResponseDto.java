package com.Aravind.DonorAndCharityManagementSystem.responsedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampaignResponseDto {
    private long id;
    private String title;
    private String description;
    private Double targetAmount;
    private Double collectionAmount;
    private String startDate;
    private String endDateTime;
    private long organizationId;
}