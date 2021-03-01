package com.leverx.mediator.repository;

import java.util.List;
import java.util.Optional;

import com.leverx.mediator.dto.request.UserRequest;
import com.leverx.mediator.dto.response.UserResponse;

/** @author Andrei Yahorau */
public interface UserRepository {

  Optional<UserResponse> save(final UserRequest userRequest);

  List<UserResponse> getAll();

  void deleteById(final long id);
}
