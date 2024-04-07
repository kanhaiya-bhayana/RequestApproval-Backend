package com.kanhaiya.requestapproval.service.impl;

import com.kanhaiya.requestapproval.dto.UserDto;
import com.kanhaiya.requestapproval.entity.UserInfo;
import com.kanhaiya.requestapproval.mapper.UserMapper;
import com.kanhaiya.requestapproval.repository.UserRepository;
import com.kanhaiya.requestapproval.service.IUserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserServiceImpl implements IUserService {
    private final UserRepository _userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder){
        userMapper = new UserMapper(new ModelMapper());
        _userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    @Transactional
    public void createUser(UserDto userDto) {
        UserInfo user = userMapper.mapToUser(userDto);

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

    @Override
    public List<UserInfo> getAllUsers() {
        return new ArrayList<>(_userRepository.findAll());
    }

    @Override
    public UserDto getUserById(String id) {
        UserInfo user = _userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        return userMapper.mapToUserDto(user);
    }

    @Override
    @Transactional
    public boolean deleteUserById(String id) {
        UserDto user = getUserById(id);
        _userRepository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean updateUser(UserDto userDto) {
        UserInfo user = _userRepository.findById(userDto.getId()).orElseThrow(
                ()-> new RuntimeException("User not found")
        );
        try {
            user = userMapper.mapToUser(userDto, user);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        _userRepository.save(user);
        return true;
    }

    private String generateNewId(String userName){
        String prefix = "U";
        StringBuilder userId = new StringBuilder(prefix);
        String chars = userName.toUpperCase().trim().replaceAll("\\s+","");
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
