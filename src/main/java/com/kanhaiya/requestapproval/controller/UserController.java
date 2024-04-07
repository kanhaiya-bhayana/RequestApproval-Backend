package com.kanhaiya.requestapproval.controller;

import com.kanhaiya.requestapproval.dto.UserDto;
import com.kanhaiya.requestapproval.entity.UserInfo;
import com.kanhaiya.requestapproval.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private IUserService _userService;

    @PostMapping("/createuserprofile")
    public String createUserProfile(@RequestBody UserDto userDto){
        _userService.createUser(userDto);
        return "Success";
    }

    @GetMapping("/getallusers")
    public List<UserInfo> getAllUsers(){
        return _userService.getAllUsers();
    }

    @GetMapping("/getuser")
    public UserDto getUser(@RequestParam String id){
        return _userService.getUserById(id);
    }

    @PutMapping("/updateuser")
    public boolean updateUser(@RequestBody UserDto userDto){
        return _userService.updateUser(userDto);
    }
    @DeleteMapping("/deleteuser")
    public boolean deleteUser(@RequestParam String id){
        return _userService.deleteUserById(id);
    }
}
