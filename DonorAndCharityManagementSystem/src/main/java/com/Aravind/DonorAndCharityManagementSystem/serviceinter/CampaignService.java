package com.Aravind.DonorAndCharityManagementSystem.serviceinter;



import com.Aravind.DonorAndCharityManagementSystem.requestdto.CampaignRequestDto;
import com.Aravind.DonorAndCharityManagementSystem.responsedto.CampaignResponseDto;

import java.util.List;

public interface CampaignService {
    CampaignResponseDto createCampaign(CampaignRequestDto dto);
    CampaignResponseDto getCampaignById(Long id);
    List<CampaignResponseDto> getAllCampaigns();
    CampaignResponseDto updateCampaign(Long id, CampaignRequestDto dto);
    void deleteCampaign(Long id);
}
