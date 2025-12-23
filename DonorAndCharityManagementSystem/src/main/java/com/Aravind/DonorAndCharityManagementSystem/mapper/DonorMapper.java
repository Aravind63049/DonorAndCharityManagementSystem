package com.Aravind.DonorAndCharityManagementSystem.mapper;

import com.Aravind.DonorAndCharityManagementSystem.model.Donor;
import com.Aravind.DonorAndCharityManagementSystem.requestdto.DonorRequestDto;
import com.Aravind.DonorAndCharityManagementSystem.responsedto.DonorResponseDto;
import org.springframework.stereotype.Component;

@Component
public class DonorMapper {

    public static Donor toEntity(DonorRequestDto dto) {
        if (dto == null) {
            return null;
        }
        Donor donor = new Donor();
        donor.setName(dto.getName());
        donor.setEmail(dto.getEmail());
        donor.setPhoneNumber(dto.getPhoneNumber());
        donor.setAddress(dto.getAddress());
        return donor;
    }


    public DonorResponseDto toDto(Donor donor) {
        if(donor == null) {
            return null;
        }
        DonorResponseDto dto = new DonorResponseDto();
        dto.setId(donor.getId());
        dto.setName(donor.getName());
        dto.setEmail(donor.getEmail());
        dto.setPhoneNumber(donor.getPhoneNumber());
        dto.setAddress(donor.getAddress());
        dto.setUserId(donor.getUser() != null ? donor.getUser().getId() : null);        return dto;
    }

    public static void updateEntityFromDto(DonorRequestDto dto, Donor donor) {
        if(dto == null || donor == null) {
            return;
        }
        if(dto.getName() != null) {
            donor.setName(dto.getName());
        }
        if(dto.getEmail() != null) {
            donor.setEmail(dto.getEmail());
        }
        if(dto.getPhoneNumber() != null) {
            donor.setPhoneNumber(dto.getPhoneNumber());
        }
        if(dto.getAddress() != null) {
            donor.setAddress(dto.getAddress());
        }
    }
}
