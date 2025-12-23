package com.Aravind.DonorAndCharityManagementSystem.mapper;

import com.Aravind.DonorAndCharityManagementSystem.model.Organization;
import com.Aravind.DonorAndCharityManagementSystem.requestdto.OrganizationRequestDto;
import com.Aravind.DonorAndCharityManagementSystem.responsedto.OrganizationResponseDto;
import org.springframework.stereotype.Component;

@Component
public class OrganizationMapper {

    public static Organization toEntity(OrganizationRequestDto dto){
        if(dto == null){
            return null;
        }
        Organization organization = new Organization();
        organization.setOrgName(dto.getOrgName());
        organization.setOrgAddress(dto.getOrgAddress());
        organization.setOrgEmail(dto.getOrgEmail());
        return organization;
    }

    public OrganizationResponseDto toDto(Organization organization){
        if(organization == null){
            return null;
        }
        OrganizationResponseDto dto = new OrganizationResponseDto();
        dto.setId(organization.getId());
        dto.setOrgName(organization.getOrgName());
        dto.setOrgAddress(organization.getOrgAddress());
        dto.setOrgEmail(organization.getOrgEmail());
        return dto;
    }

    public void updateEntityFromDto(Organization organization, OrganizationRequestDto dto){
        if(dto == null || organization == null){
            return;
        }
        if(dto.getOrgName() != null){
            organization.setOrgName(dto.getOrgName());
        }
        if(dto.getOrgAddress() != null) {
            organization.setOrgAddress(dto.getOrgAddress());
        }
        if(dto.getOrgEmail() != null) {
            organization.setOrgEmail(dto.getOrgEmail());
        }
    }
}

