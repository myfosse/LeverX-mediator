package com.leverx.mediator.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leverx.mediator.dto.request.UserRequest;
import com.leverx.mediator.dto.response.UserResponse;
import com.leverx.mediator.repository.UserRepository;
import com.leverx.mediator.service.UserService;

import lombok.extern.slf4j.Slf4j;

/** @author Andrei Yahorau */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserResponse save(final UserRequest userRequest) {
    log.info("Service. Save user: {}", userRequest);

    return userRepository.save(userRequest);
  }

  @Override
  public List<UserResponse> getAll() {
    log.info("Service. Get all users");

    return userRepository.getAll();
  }

  @Override
  public void deleteById(final long id) {
    log.info("Service. Delete user by id: {}", id);

    userRepository.deleteById(id);
  }
}
