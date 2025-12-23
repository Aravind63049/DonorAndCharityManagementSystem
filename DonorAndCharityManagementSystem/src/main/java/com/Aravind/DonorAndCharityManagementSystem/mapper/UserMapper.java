package com.Aravind.DonorAndCharityManagementSystem.mapper;

import com.Aravind.DonorAndCharityManagementSystem.model.User;
import com.Aravind.DonorAndCharityManagementSystem.requestdto.UserRequestDto;
import com.Aravind.DonorAndCharityManagementSystem.responsedto.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserRequestDto dto){
        if(dto == null){
            return null;
        }
        User user = new User();
        user.setUserName(dto.getUserName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setRole(dto.getRole());
        return user;
    }

    public UserResponseDto toDto(User user){
        if (user == null){
            return null;
        }
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setUserName(user.getUserName());
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setRole(user.getRole());
        return dto;
    }

    public static void updateEntityFromDto(UserRequestDto dto, User user){
        if(dto == null || user == null){
            return;
        }
        if(dto.getUserName() != null){
            user.setUserName(dto.getUserName());
        }
        if(dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }
        if(dto.getPassword() != null) {
            user.setPassword(dto.getPassword());
        }
        if(dto.getPhoneNumber() != null) {
            user.setPhoneNumber(dto.getPhoneNumber());
        }
        if(dto.getRole() != null) {
            user.setRole(dto.getRole());
        }
    }
}
