package com.Aravind.DonorAndCharityManagementSystem.serviceimpl;


import com.Aravind.DonorAndCharityManagementSystem.mapper.DonorMapper;
import com.Aravind.DonorAndCharityManagementSystem.model.Donor;
import com.Aravind.DonorAndCharityManagementSystem.model.User;
import com.Aravind.DonorAndCharityManagementSystem.repository.DonorRepository;
import com.Aravind.DonorAndCharityManagementSystem.repository.UserRepository;
import com.Aravind.DonorAndCharityManagementSystem.requestdto.DonorRequestDto;
import com.Aravind.DonorAndCharityManagementSystem.responsedto.DonorResponseDto;
import com.Aravind.DonorAndCharityManagementSystem.serviceinter.DonorService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class DonorServiceImpl implements DonorService {

    public final DonorRepository donorRepository;
    public final DonorMapper donorMapper;
    public final UserRepository userRepository;

    public DonorServiceImpl(DonorRepository donorRepository, DonorMapper donorMapper, UserRepository userRepository) {
        this.donorRepository = donorRepository;
        this.donorMapper = donorMapper;
        this.userRepository = userRepository;
    }


    @Override
    public DonorResponseDto createDonor(DonorRequestDto dto) {
        Donor donor = donorMapper.toEntity(dto);
        if(dto.getUserId() != null){
            User user = userRepository.findById(dto.getUserId()).orElseThrow(()-> new RuntimeException("User not found with id: " + dto.getUserId()));
            donor.setUser(user);
        }
        Donor saved = donorRepository.save(donor);
        return donorMapper.toDto(saved);
    }

    @Override
    public DonorResponseDto getDonorById(Long id) {
        Donor donor = donorRepository.findById(id).orElseThrow(() -> new RuntimeException("Donor not found with id: " + id));
        return donorMapper.toDto(donor);
    }

    @Override
    public List<DonorResponseDto> getAllDonors() {
        return donorRepository.findAll().stream().map(donorMapper::toDto).toList();
    }

    @Override
    public DonorResponseDto updateDonor(Long id, DonorRequestDto dto) {
        Donor donor = donorRepository.findById(id).orElseThrow(() -> new RuntimeException("Donor not found with id: " + id));
        DonorMapper.updateEntityFromDto(dto, donor);
        if(dto.getUserId() != null){
            User user = userRepository.findById(dto.getUserId()).orElseThrow(()-> new RuntimeException("User not found with id: " + dto.getUserId()));
            donor.setUser(user);
        }
        Donor saved = donorRepository.save(donor);
        return donorMapper.toDto(saved);
    }

    @Override
    public void deleteDonor(Long id) {
        Donor donor = donorRepository.findById(id).orElseThrow(() -> new RuntimeException("Donor not found with id: " + id));
        donorRepository.delete(donor);
    }
}
