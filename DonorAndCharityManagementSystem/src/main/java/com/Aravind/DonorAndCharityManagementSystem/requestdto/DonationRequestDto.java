package com.Aravind.DonorAndCharityManagementSystem.requestdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonationRequestDto {
    private Double amount;
    private String donationType;
    private String description;
    private LocalDateTime donationDate;
    private Long donorId;
    private Long campaignId;
}
