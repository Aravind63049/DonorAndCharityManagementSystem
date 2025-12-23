package com.Aravind.DonorAndCharityManagementSystem.serviceinter;



import com.Aravind.DonorAndCharityManagementSystem.requestdto.UserRequestDto;
import com.Aravind.DonorAndCharityManagementSystem.responsedto.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto createUser(UserRequestDto dto);
    UserResponseDto getUserById(Long userId);
    List<UserResponseDto> getAllUsers();
    UserResponseDto updateUser(Long userId, UserRequestDto dto);
    void deleteUser(Long userId);
}
