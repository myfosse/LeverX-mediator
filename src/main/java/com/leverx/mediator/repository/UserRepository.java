package com.leverx.mediator.repository;

import java.util.List;

import com.leverx.mediator.dto.response.UserResponseDto;

/** @author Andrei Yahorau */
public interface UserRepository {

  UserResponseDto save();

  List<UserResponseDto> getAll();
}
