package com.fezor.spring_finance_control.mapper;

import com.fezor.spring_finance_control.dto.UserRequest;
import com.fezor.spring_finance_control.dto.UserResponse;
import com.fezor.spring_finance_control.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserRequest request);

    UserResponse toResponse(User user);

    List<UserResponse> toResponseList(List<User> users);
}
