package com.Aravind.DonorAndCharityManagementSystem.mapper;

import com.Aravind.DonorAndCharityManagementSystem.model.FundAllocation;
import com.Aravind.DonorAndCharityManagementSystem.requestdto.FundAllocationRequestDto;
import com.Aravind.DonorAndCharityManagementSystem.responsedto.FundAllocationResponseDto;
import org.springframework.stereotype.Component;

@Component
public class FundAllocationMapper {

    public static FundAllocation toEntity(FundAllocationRequestDto dto) {
        if (dto == null) {
            return null;
        }
        FundAllocation fundAllocation = new FundAllocation();
        fundAllocation.setAmountUsed(dto.getAmountUsed());
        fundAllocation.setUsageDescription(dto.getUsageDescription());
        fundAllocation.setUsedDate(dto.getUsedDate());
        return fundAllocation;
    }

    public FundAllocationResponseDto toDto(FundAllocation fundAllocation) {
        if (fundAllocation == null) {
            return null;
        }
        FundAllocationResponseDto dto = new FundAllocationResponseDto();
        dto.setId(fundAllocation.getId());
        dto.setAmountUsed(fundAllocation.getAmountUsed());
        dto.setUsageDescription(fundAllocation.getUsageDescription());
        dto.setUsedDate(fundAllocation.getUsedDate().toString());
        dto.setOrganizationId(fundAllocation.getOrganization() != null ? fundAllocation.getOrganization().getId() : null);
        dto.setCampaignId(fundAllocation.getCampaign() != null ? fundAllocation.getCampaign().getId() : null);
        return dto;
    }

    public void updateEntityFromDto(FundAllocationRequestDto dto, FundAllocation fundAllocation) {
        if (dto == null || fundAllocation == null) {
            return;
        }
        if(dto.getAmountUsed() != null) {
            fundAllocation.setAmountUsed(dto.getAmountUsed());
        }
        if(dto.getUsageDescription() != null) {
            fundAllocation.setUsageDescription(dto.getUsageDescription());
        }
        if(dto.getUsedDate() != null) {
            fundAllocation.setUsedDate(dto.getUsedDate());
        }
    }
}