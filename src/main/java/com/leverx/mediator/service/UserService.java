package com.leverx.mediator.service;

import java.util.List;

import com.leverx.mediator.dto.request.UserRequestDto;
import com.leverx.mediator.dto.response.UserResponseDto;

/** @author Andrei Yahorau */
public interface UserService {

  UserResponseDto save(final UserRequestDto catRequestDto);

  List<UserRequestDto> getAll();
}
