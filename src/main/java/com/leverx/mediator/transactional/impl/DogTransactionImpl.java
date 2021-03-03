package com.leverx.mediator.transactional.impl;

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

  private long dogId;
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
      dogId = dogResponse.getId();
      return dogResponse;
    } catch (final HttpClientErrorException exception) {
      log.error("DogTransaction. Can't save dog: {}", dog);

      throw exception;
    }
  }

  @Override
  public void rollback() {
    log.info("CatTransaction. Rollback dog with id: {}", dogId);

    dogRepository.deleteById(dogId);
  }
}
