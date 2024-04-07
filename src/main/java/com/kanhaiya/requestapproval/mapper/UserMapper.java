package com.kanhaiya.requestapproval.mapper;

import com.kanhaiya.requestapproval.dto.UserDto;
import com.kanhaiya.requestapproval.entity.UserInfo;
import org.modelmapper.ModelMapper;

import java.lang.reflect.Field;


public class UserMapper {
    private final ModelMapper modelMapper;
    public UserMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }
    public UserDto mapToUserDto(UserInfo user){
//        userDto.setId(user.getId());
//        userDto.setUserName(user.getUserName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        return  userDto;
        return modelMapper.map(user, UserDto.class);
    }
    public UserInfo mapToUser(UserDto userDto, UserInfo userDb) throws IllegalAccessException {
            Class<UserDto> dtoClass = UserDto.class;
            Field[] fields = dtoClass.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(userDto);
                if (value == null) {
                    Field userDbField;
                    try {
                        userDbField = userDb.getClass().getDeclaredField(field.getName());
                        userDbField.setAccessible(true);
                        Object dbValue = userDbField.get(userDb);
                        field.set(userDto, dbValue);
                    } catch (NoSuchFieldException e) {
                        // Handle if the field doesn't exist in userDb
                        e.printStackTrace();
                    }
                }
            }

            return mapToUser(userDto); // Assuming there's a method to convert UserDto to UserInfo
        }

    public UserInfo mapToUser(UserDto userDto){
        return modelMapper.map(userDto, UserInfo.class);
    }
}
