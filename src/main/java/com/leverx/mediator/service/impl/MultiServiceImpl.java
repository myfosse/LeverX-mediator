package com.leverx.mediator.service.impl;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.leverx.mediator.dto.request.multi.UserCatDogRequest;
import com.leverx.mediator.dto.response.CatResponse;
import com.leverx.mediator.dto.response.DogResponse;
import com.leverx.mediator.dto.response.UserResponse;
import com.leverx.mediator.dto.response.multi.UserCatDogListResponse;
import com.leverx.mediator.dto.response.multi.UserCatDogResponse;
import com.leverx.mediator.repository.CatRepository;
import com.leverx.mediator.repository.DogRepository;
import com.leverx.mediator.repository.UserRepository;
import com.leverx.mediator.service.MultiService;

import lombok.extern.slf4j.Slf4j;

/** @author Andrei Yahorau */
@Service
@Slf4j
public class MultiServiceImpl implements MultiService {

  private final CatRepository catRepository;
  private final DogRepository dogRepository;
  private final UserRepository userRepository;

  @Autowired
  public MultiServiceImpl(
      final CatRepository catRepository,
      final DogRepository dogRepository,
      final UserRepository userRepository) {
    this.catRepository = catRepository;
    this.dogRepository = dogRepository;
    this.userRepository = userRepository;
  }

  @Override
  public UserCatDogResponse save(final UserCatDogRequest userCatDogRequest) {
    log.info("Service. Save user, cat, dog: {}", userCatDogRequest);

    UserResponse userResponse = userRepository.save(userCatDogRequest.getUser())
        .orElseThrow(() -> {
          log.error("Service. Can't save user: {}", userCatDogRequest.getUser());

          return new HttpClientErrorException(BAD_REQUEST);
        });

    CatResponse catResponse = catRepository.save(userCatDogRequest.getCat())
        .orElseThrow(() -> {
          log.error("Service. Can't save cat: {}", userCatDogRequest.getCat());

          userRepository.deleteById(userResponse.getId());
          return new HttpClientErrorException(BAD_REQUEST);
        });

    DogResponse dogResponse = dogRepository.save(userCatDogRequest.getDog())
        .orElseThrow(() -> {
          log.error("Service. Can't save dog: {}", userCatDogRequest.getDog());

          userRepository.deleteById(userResponse.getId());
          catRepository.deleteById(catResponse.getId());
          return new HttpClientErrorException(BAD_REQUEST);
        });

    return UserCatDogResponse.builder()
        .user(userResponse)
        .cat(catResponse)
        .dog(dogResponse)
        .build();
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
