package com.leverx.mediator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leverx.mediator.dto.request.multi.UserCatDogRequest;
import com.leverx.mediator.dto.response.multi.UserCatDogListResponse;
import com.leverx.mediator.dto.response.multi.UserCatDogResponse;
import com.leverx.mediator.repository.CatRepository;
import com.leverx.mediator.repository.DogRepository;
import com.leverx.mediator.repository.UserRepository;
import com.leverx.mediator.service.MultiService;
import com.leverx.mediator.transactional.UserCatDogTransaction;

import lombok.extern.slf4j.Slf4j;

/** @author Andrei Yahorau */
@Service
@Slf4j
public class MultiServiceImpl implements MultiService {

  private final CatRepository catRepository;
  private final DogRepository dogRepository;
  private final UserRepository userRepository;
  private final UserCatDogTransaction userCatDogTransaction;

  @Autowired
  public MultiServiceImpl(
      final CatRepository catRepository,
      final DogRepository dogRepository,
      final UserRepository userRepository,
      final UserCatDogTransaction userCatDogTransaction) {
    this.catRepository = catRepository;
    this.dogRepository = dogRepository;
    this.userRepository = userRepository;
    this.userCatDogTransaction = userCatDogTransaction;
  }

  @Override
  public UserCatDogResponse save(final UserCatDogRequest userCatDogRequest) {
    log.info("Service. Save user, cat, dog: {}", userCatDogRequest);

    return userCatDogTransaction.executeSave(userCatDogRequest);
  }

  @Override
  public UserCatDogListResponse getAllLists() {
    log.info("Service. Get lists of users, cats, dogs");

    return UserCatDogListResponse.builder()
        .cats(catRepository.getAll())
        .dogs(dogRepository.getAll())
        .users(userRepository.getAll())
        .build();
  }
}
