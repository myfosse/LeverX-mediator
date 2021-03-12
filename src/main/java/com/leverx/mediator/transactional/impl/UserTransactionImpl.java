package com.leverx.mediator.transactional.impl;

import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;
import static org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes;

import static com.leverx.mediator.transactional.constant.AttributeConstants.USER_ID;

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
      currentRequestAttributes().setAttribute(USER_ID, userResponse.getId(), SCOPE_REQUEST);
      return userResponse;
    } catch (final HttpClientErrorException exception) {
      log.error("UserTransaction. Can't save user: {}", user);

      throw exception;
    }
  }

  @Override
  public void rollback() {

    long id = (long) currentRequestAttributes().getAttribute(USER_ID, SCOPE_REQUEST);

    log.info("UserTransaction. Rollback user with id: {}", id);

    userRepository.deleteById(id);
  }
}
