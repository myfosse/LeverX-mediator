package com.leverx.mediator.service;

import java.util.List;

import com.leverx.mediator.dto.request.UserRequest;
import com.leverx.mediator.dto.response.UserResponse;

/** @author Andrei Yahorau */
public interface UserService {

  UserResponse save(final UserRequest userRequest);

  List<UserResponse> getAll();

  void deleteById(final long id);
}
