package com.kanhaiya.requestapproval.service;

import com.kanhaiya.requestapproval.dto.UserDto;
import com.kanhaiya.requestapproval.entity.UserInfo;

import java.util.List;

public interface IUserService {
    void createUser(UserDto userDto);
    List<UserInfo>  getAllUsers();
    UserDto getUserById(String id);
    boolean deleteUserById(String id);
    boolean updateUser(UserDto userDto);

}
