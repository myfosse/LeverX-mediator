package com.leverx.mediator.transactional.impl;

import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;
import static org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes;

import static com.leverx.mediator.transactional.constant.AttributeConstants.DOG_ID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import com.leverx.mediator.dto.request.DogRequest;
import com.leverx.mediator.dto.response.DogResponse;
import com.leverx.mediator.repository.DogRepository;
import com.leverx.mediator.transactional.DogTransaction;

import lombok.extern.slf4j.Slf4j;

/** @author Andrei Yahorau */
@Component
@Slf4j
public class DogTransactionImpl implements DogTransaction {

  private final DogRepository dogRepository;

  @Autowired
  public DogTransactionImpl(final DogRepository dogRepository) {
    this.dogRepository = dogRepository;
  }

  @Override
  public DogResponse executeSave(final DogRequest dog) {
    log.info("DogTransaction. Save dog: {}", dog);

    try {
      DogResponse dogResponse = dogRepository.save(dog);
      currentRequestAttributes().setAttribute(DOG_ID, dogResponse.getId(), SCOPE_REQUEST);
      return dogResponse;
    } catch (final HttpClientErrorException exception) {
      log.error("DogTransaction. Can't save dog: {}", dog);

      throw exception;
    }
  }

  @Override
  public void rollback() {

    long id = (long) currentRequestAttributes().getAttribute(DOG_ID, SCOPE_REQUEST);

    log.info("CatTransaction. Rollback dog with id: {}", id);

    dogRepository.deleteById(id);
  }
}
