package com.leverx.mediator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leverx.mediator.dto.request.CatRequestDto;
import com.leverx.mediator.dto.request.DogRequestDto;
import com.leverx.mediator.dto.request.UserRequestDto;
import com.leverx.mediator.dto.response.every.EveryListResponse;
import com.leverx.mediator.dto.response.every.EverySingleResponse;
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
  public EverySingleResponse save(CatRequestDto catRequestDto, DogRequestDto dogRequestDto, UserRequestDto userRequestDto) {
    return null;
  }

  @Override
  public EveryListResponse getAllLists() {
    log.info("Service. Get all lists");

    return EveryListResponse.builder()
        .cats(catRepository.getAll())
        .dogs(dogRepository.getAll())
        .users(userRepository.getAll())
        .build();
  }
}
