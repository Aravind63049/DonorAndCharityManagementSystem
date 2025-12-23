package com.Aravind.DonorAndCharityManagementSystem.serviceimpl;


import com.Aravind.DonorAndCharityManagementSystem.mapper.OrganizationMapper;
import com.Aravind.DonorAndCharityManagementSystem.model.Organization;
import com.Aravind.DonorAndCharityManagementSystem.repository.OrganizationRepository;
import com.Aravind.DonorAndCharityManagementSystem.requestdto.OrganizationRequestDto;
import com.Aravind.DonorAndCharityManagementSystem.responsedto.OrganizationResponseDto;
import com.Aravind.DonorAndCharityManagementSystem.serviceinter.OrganizationService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {

    public final OrganizationRepository organizationRepository;
    public final OrganizationMapper organizationMapper;

    public OrganizationServiceImpl(OrganizationRepository organizationRepository, OrganizationMapper organizationMapper) {
        this.organizationRepository = organizationRepository;
        this.organizationMapper = organizationMapper;
    }

    @Override
    public OrganizationResponseDto createOrganization(OrganizationRequestDto dto) {
        Organization organization = OrganizationMapper.toEntity(dto);
        Organization saved = organizationRepository.save(organization);
        return organizationMapper.toDto(saved);
    }

    @Override
    public OrganizationResponseDto getOrganizationById(Long organizationId) {
        Organization organization = organizationRepository.findById(organizationId).orElseThrow(() -> new RuntimeException("Organization not found with id: " + organizationId));
        return organizationMapper.toDto(organization);
    }

    @Override
    public List<OrganizationResponseDto> getAllOrganizations() {
        return organizationRepository.findAll().stream().map(organizationMapper::toDto).toList();
    }

    @Override
    public OrganizationResponseDto updateOrganization(Long organizationId, OrganizationRequestDto dto) {
        Organization organization = organizationRepository.findById(organizationId).orElseThrow(() -> new RuntimeException("Organization not found with id: " + organizationId));
        organizationMapper.updateEntityFromDto(organization, dto);
        Organization saved = organizationRepository.save(organization);
        return organizationMapper.toDto(saved);
    }

    @Override
    public void deleteOrganization(Long organizationId) {
        Organization organization = organizationRepository.findById(organizationId).orElseThrow(() -> new RuntimeException("Organization not found with id: " + organizationId));
        organizationRepository.delete(organization);
    }
}
