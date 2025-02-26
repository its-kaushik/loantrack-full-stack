package com.example.loantrack.user;

import com.example.loantrack.auth.dto.SignUpRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUser(SignUpRequestDTO user);
}