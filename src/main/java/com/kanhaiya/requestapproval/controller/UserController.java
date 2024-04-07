package com.kanhaiya.requestapproval.controller;

import com.kanhaiya.requestapproval.constants.RequestApprovalConstants;
import com.kanhaiya.requestapproval.dto.ResponseDto;
import com.kanhaiya.requestapproval.dto.UserDto;
import com.kanhaiya.requestapproval.entity.UserInfo;
import com.kanhaiya.requestapproval.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/user", produces = {MediaType.APPLICATION_JSON_VALUE})
public class UserController {

    private IUserService _userService;

    @PostMapping("/createuserprofile")
    public ResponseEntity<ResponseDto> createUserProfile(@RequestBody UserDto userDto){
        _userService.createUser(userDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(RequestApprovalConstants.STATUS_201, RequestApprovalConstants.MESSAGE_201));
    }

    @GetMapping("/getallusers")
    public ResponseEntity<List<UserInfo>> getAllUsers(){
        List<UserInfo> users = _userService.getAllUsers();
        return ResponseEntity
                .status(HttpStatus.OK).body(users);
    }

    @GetMapping("/getuser")
    public ResponseEntity<UserDto> getUser(@RequestParam String id){
        UserDto response = _userService.getUserById(id);
        return ResponseEntity
                .status(HttpStatus.OK).body(response);
    }

    @PutMapping("/updateuser")
    public ResponseEntity<ResponseDto> updateUser(@RequestBody UserDto userDto){
        String response = String.valueOf(_userService.updateUser(userDto));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(RequestApprovalConstants.STATUS_200, response));
    }
    @DeleteMapping("/deleteuser")
    public ResponseEntity<ResponseDto> deleteUser(@RequestParam String id){
        String response = String.valueOf(_userService.deleteUserById(id));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(RequestApprovalConstants.STATUS_200, response));
    }
}
