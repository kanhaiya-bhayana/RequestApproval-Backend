package com.kanhaiya.requestapproval.service;

import com.kanhaiya.requestapproval.dto.UserDto;
import com.kanhaiya.requestapproval.entity.UserInfo;

import java.util.List;

public interface IUserService {
    /**
     *
     * @param userDto - userDto Object
     */
    void createUser(UserDto userDto);

    /**
     *
     * @return - List that contains all users
     */
    List<UserInfo>  getAllUsers();

    /**
     *
     * @param id - String user id
     * @return - UserDto - Object
     */
    UserDto getUserById(String id);

    /**
     *
     * @param id - String used id
     * @return - boolean
     */
    boolean deleteUserById(String id);

    /**
     *
     * @param userDto - userDto Object
     * @return - boolean
     */
    boolean updateUser(UserDto userDto);

}
