package com.leverx.mediator.transactional.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import com.leverx.mediator.dto.request.UserRequest;
import com.leverx.mediator.dto.response.UserResponse;
import com.leverx.mediator.repository.UserRepository;
import com.leverx.mediator.transactional.UserTransaction;

import lombok.extern.slf4j.Slf4j;

/** @author Andrei Yahorau */
@Component
@Slf4j
public class UserTransactionImpl implements UserTransaction {

  private long userId;
  private final UserRepository userRepository;

  @Autowired
  public UserTransactionImpl(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserResponse executeSave(final UserRequest user) {
    log.info("UserTransaction. Save user: {}", user);

    try {
      UserResponse userResponse = userRepository.save(user);
      userId = userResponse.getId();
      return userResponse;
    } catch (final HttpClientErrorException exception) {
      log.error("UserTransaction. Can't save user: {}", user);

      throw exception;
    }
  }

  @Override
  public void rollback() {
    log.info("UserTransaction. Rollback user with id: {}", userId);

    userRepository.deleteById(userId);
  }
}
