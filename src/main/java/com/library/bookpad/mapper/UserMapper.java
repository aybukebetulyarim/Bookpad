package com.library.bookpad.mapper;

import com.library.bookpad.entity.User;
import com.library.bookpad.resource.response.user.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "id", target = "id")
    UserResponse entityToResponse(User user);

    List<UserResponse> entityListToResponseList(List<User> userList);

    @Mapping(source = "id", target = "id")
    User responseEntity(UserResponse userResponse);
}
