package com.Aravind.DonorAndCharityManagementSystem.mapper;


import com.Aravind.DonorAndCharityManagementSystem.model.Donation;
import com.Aravind.DonorAndCharityManagementSystem.requestdto.DonationRequestDto;
import com.Aravind.DonorAndCharityManagementSystem.responsedto.DonationResponseDto;
import org.springframework.stereotype.Component;

@Component
public class DonationMapper {

    public static Donation toEntity(DonationRequestDto dto){
        if(dto == null){
            return null;
        }
        Donation donation = new Donation();
        donation.setAmount(dto.getAmount());
        donation.setDonationType(dto.getDonationType());
        donation.setDescription(dto.getDescription());
        donation.setDonationDate(dto.getDonationDate());
        return donation;
    }

    public DonationResponseDto toDto(Donation donation){
        if (donation == null) return null;
        DonationResponseDto dto = new DonationResponseDto();
        dto.setId(donation.getId());
        dto.setAmount(donation.getAmount());
        dto.setDonationType(donation.getDonationType());
        dto.setDescription(donation.getDescription());
        dto.setDonationDate(donation.getDonationDate() != null ? donation.getDonationDate() : null);
        dto.setDonorId(donation.getDonor() != null ? donation.getDonor().getId() : null);
        dto.setCampaignId(donation.getCampaign() != null ? donation.getCampaign().getId() : null);
        return dto;
    }


    public void updateEntityFromDto(DonationRequestDto dto, Donation donation){
        if(dto == null || donation == null){
            return;
        }
        if(dto.getAmount() != null){
            donation.setAmount(dto.getAmount());
        }
        if(dto.getDonationType() != null) {
            donation.setDonationType(dto.getDonationType());
        }
        if(dto.getDescription() != null) {
            donation.setDescription(dto.getDescription());
        }
        if(dto.getDonationDate() != null) {
            donation.setDonationDate(dto.getDonationDate());
        }
    }

}



