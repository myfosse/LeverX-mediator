package com.leverx.mediator.transactional.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import com.leverx.mediator.dto.request.multi.UserCatDogRequest;
import com.leverx.mediator.dto.response.CatResponse;
import com.leverx.mediator.dto.response.DogResponse;
import com.leverx.mediator.dto.response.UserResponse;
import com.leverx.mediator.dto.response.multi.UserCatDogResponse;
import com.leverx.mediator.transactional.CatTransaction;
import com.leverx.mediator.transactional.DogTransaction;
import com.leverx.mediator.transactional.Transaction;
import com.leverx.mediator.transactional.UserCatDogTransaction;
import com.leverx.mediator.transactional.UserTransaction;

import lombok.extern.slf4j.Slf4j;

/** @author Andrei Yahorau */
@Component
@Slf4j
public class UserCatDogTransactionImpl implements UserCatDogTransaction {

  private final UserTransaction userTransaction;
  private final CatTransaction catTransaction;
  private final DogTransaction dogTransaction;

  @Autowired
  public UserCatDogTransactionImpl(
      final UserTransaction userTransaction,
      final CatTransaction catTransaction,
      final DogTransaction dogTransaction) {
    this.userTransaction = userTransaction;
    this.catTransaction = catTransaction;
    this.dogTransaction = dogTransaction;
  }

  @Override
  public UserCatDogResponse executeSave(final UserCatDogRequest userCatDog) {
    log.info("UserCatDogTransaction. Save use, cat, dog: {}", userCatDog);

    List<Transaction> transactionContext = new ArrayList<>();

    UserResponse userResponse;
    CatResponse catResponse;
    DogResponse dogResponse;

    try {
      userResponse = userTransaction.executeSave(userCatDog.getUser());
      transactionContext.add(userTransaction);
      catResponse = catTransaction.executeSave(userCatDog.getCat());
      transactionContext.add(catTransaction);
      dogResponse = dogTransaction.executeSave(userCatDog.getDog());
      transactionContext.add(dogTransaction);
    } catch (final HttpClientErrorException exception) {
      log.error("UserCatDogTransaction. Rollback use, cat, dog: {}", userCatDog);

      transactionContext.forEach(Transaction::rollback);

      throw exception;
    }

    return UserCatDogResponse.builder()
        .user(userResponse)
        .cat(catResponse)
        .dog(dogResponse)
        .build();
  }
}
