package com.kanhaiya.requestapproval.mapper;

import com.kanhaiya.requestapproval.dto.UserDto;
import com.kanhaiya.requestapproval.entity.UserInfo;

public class UserMapper {
    public static UserDto mapToUserDto(UserInfo user, UserDto userDto){
        userDto.setUserName(user.getUserName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        return  userDto;
    }

    public static UserInfo mapToUser(UserDto userDto, UserInfo user){
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return user;
    }
}
