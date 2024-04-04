package com.pluralsight.project.mappers;

import com.pluralsight.project.dtos.responses.UserResponse;
import com.pluralsight.project.models.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    List<UserResponse> listUserResponse(List<User> users);

    User UserResponseToUser(UserResponse userResponse);
    UserResponse UserToUserResponse(User user);

}
