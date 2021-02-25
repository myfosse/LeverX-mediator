package com.leverx.mediator.service.impl;

import static java.util.Arrays.asList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
  public List<Object> getAllLists() {
    log.info("Service. Get all lists");

    return asList(
        catRepository.getAllCats(),
        dogRepository.getAllDogs(),
        userRepository.getAllUsers());
  }
}
