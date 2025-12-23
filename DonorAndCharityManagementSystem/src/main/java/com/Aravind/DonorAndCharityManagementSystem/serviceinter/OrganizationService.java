package com.Aravind.DonorAndCharityManagementSystem.serviceinter;



import com.Aravind.DonorAndCharityManagementSystem.requestdto.OrganizationRequestDto;
import com.Aravind.DonorAndCharityManagementSystem.responsedto.OrganizationResponseDto;

import java.util.List;

public interface OrganizationService {
    OrganizationResponseDto createOrganization(OrganizationRequestDto dto);
    OrganizationResponseDto getOrganizationById(Long organizationId);
    List<OrganizationResponseDto> getAllOrganizations();
    OrganizationResponseDto updateOrganization(Long organizationId, OrganizationRequestDto dto);
    void deleteOrganization(Long organizationId);
}
