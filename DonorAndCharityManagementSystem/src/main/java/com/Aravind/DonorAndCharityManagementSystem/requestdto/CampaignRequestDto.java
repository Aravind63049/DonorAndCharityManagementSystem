package com.Aravind.DonorAndCharityManagementSystem.requestdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CampaignRequestDto {
    private String title;
    private String description;
    private Double targetAmount;
    private Double collectionAmount;
    private LocalDateTime startDate;
    private LocalDateTime endDateTime;
    private Long organizationId;
}