package com.Aravind.DonorAndCharityManagementSystem.mapper;


import com.Aravind.DonorAndCharityManagementSystem.model.Campaign;
import com.Aravind.DonorAndCharityManagementSystem.requestdto.CampaignRequestDto;
import com.Aravind.DonorAndCharityManagementSystem.responsedto.CampaignResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CampaignMapper {

    public static Campaign toEntity(CampaignRequestDto dto){
        if(dto == null){
            return null;
        }
        Campaign campaign = new Campaign();
        campaign.setTitle(dto.getTitle());
        campaign.setDescription(dto.getDescription());
        campaign.setTargetAmount(dto.getTargetAmount());
        campaign.setCollectionAmount(dto.getCollectionAmount());
        campaign.setStartDate(dto.getStartDate());
        campaign.setEndDateTime(dto.getEndDateTime());
        return campaign;
    }

    public CampaignResponseDto toDto(Campaign entity){
        if(entity == null){
            return null;
        }
        CampaignResponseDto dto = new CampaignResponseDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setTargetAmount(entity.getTargetAmount());
        dto.setCollectionAmount(entity.getCollectionAmount());
        dto.setStartDate(entity.getStartDate().toString());
        dto.setEndDateTime(entity.getEndDateTime().toString());
        dto.setOrganizationId(entity.getOrganization() != null ? entity.getOrganization().getId() : null);
        return dto;
    }


    public void updateEntityFromDto (Campaign campaign, CampaignRequestDto dto){
        if(dto == null || campaign == null){
            return;
        }
        if(campaign.getTitle() != null){
            campaign.setTitle(dto.getTitle());
        }
        if(campaign.getDescription() != null){
            campaign.setDescription(dto.getDescription());
        }
        if(campaign.getTargetAmount() != null){
            campaign.setTargetAmount(dto.getTargetAmount());
        }
        if(campaign.getCollectionAmount() != null) {
            campaign.setCollectionAmount(dto.getCollectionAmount());
        }
        if(campaign.getStartDate() != null) {
            campaign.setStartDate(dto.getStartDate());
        }
        if(campaign.getEndDateTime() != null) {
            campaign.setEndDateTime(dto.getEndDateTime());
        }
    }
}