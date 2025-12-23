package com.Aravind.DonorAndCharityManagementSystem.responsedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonationResponseDto {
    private long id;
    private Double amount;
    private String donationType;
    private String description;
    private LocalDateTime donationDate;
    private long donorId;
    private long campaignId;
}
