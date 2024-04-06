package com.kanhaiya.requestapproval.service.impl;

import com.kanhaiya.requestapproval.dto.UserDto;
import com.kanhaiya.requestapproval.entity.UserInfo;
import com.kanhaiya.requestapproval.mapper.UserMapper;
import com.kanhaiya.requestapproval.repository.UserRepository;
import com.kanhaiya.requestapproval.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    private UserRepository _userRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public void createUser(UserDto userDto) {
        UserInfo user = UserMapper.mapToUser(userDto, new UserInfo());

        if (user.getId() != null){
            Optional<UserInfo> optionalUer = _userRepository.findById(user.getId());
            if (optionalUer.isPresent()){
                throw new RuntimeException("Already exist");
            }
        }

        user.setId(generateNewId(user.getUserName()));
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        _userRepository.save(user);
    }

    private String generateNewId(String userName){
        String prefix = "U";
        StringBuilder userId = new StringBuilder(prefix);
        String chars = userName.toUpperCase();
        Random rand = new Random();
        String numbers = "0123456789";
        for (int i = 0; i < 4; i++) {
            int index = rand.nextInt(chars.length());
            userId.append(chars.charAt(index));
            userId.append(numbers.charAt(index));
        }
        return userId.toString();
    }
}
