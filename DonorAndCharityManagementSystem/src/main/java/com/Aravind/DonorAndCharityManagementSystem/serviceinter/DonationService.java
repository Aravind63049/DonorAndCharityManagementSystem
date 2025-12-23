package com.Aravind.DonorAndCharityManagementSystem.serviceinter;



import com.Aravind.DonorAndCharityManagementSystem.requestdto.DonationRequestDto;
import com.Aravind.DonorAndCharityManagementSystem.responsedto.DonationResponseDto;

import java.util.List;

public interface DonationService {
    DonationResponseDto createDonation(DonationRequestDto dto);
    DonationResponseDto getDonationById(Long id);
    List<DonationResponseDto> getAllDonations();
    DonationResponseDto updateDonation(Long id, DonationRequestDto dto);
    void deleteDonation(Long id);
}
