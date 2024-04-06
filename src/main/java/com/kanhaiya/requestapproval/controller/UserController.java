package com.kanhaiya.requestapproval.controller;

import com.kanhaiya.requestapproval.dto.UserDto;
import com.kanhaiya.requestapproval.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private IUserService _userService;

    @GetMapping("/getuser")
    public String getUser(){
        return "Hello World";
    }

    @PostMapping("/createuserprofile")
    public String createUserProfile(@RequestBody UserDto userDto){
        _userService.createUser(userDto);
        return "Success";
    }
}
