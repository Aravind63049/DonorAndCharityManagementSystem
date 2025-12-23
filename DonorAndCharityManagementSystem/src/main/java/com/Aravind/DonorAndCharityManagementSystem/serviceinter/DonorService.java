package com.Aravind.DonorAndCharityManagementSystem.serviceinter;



import com.Aravind.DonorAndCharityManagementSystem.requestdto.DonorRequestDto;
import com.Aravind.DonorAndCharityManagementSystem.responsedto.DonorResponseDto;

import java.util.List;

public interface DonorService {
    DonorResponseDto createDonor(DonorRequestDto dto);
    DonorResponseDto getDonorById(Long id);
    List<DonorResponseDto> getAllDonors();
    DonorResponseDto updateDonor(Long id, DonorRequestDto dto);
    void deleteDonor(Long id);

}
