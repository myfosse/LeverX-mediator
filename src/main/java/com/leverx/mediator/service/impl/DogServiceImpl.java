package com.leverx.mediator.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leverx.mediator.dto.request.DogRequest;
import com.leverx.mediator.dto.response.DogResponse;
import com.leverx.mediator.repository.DogRepository;
import com.leverx.mediator.service.DogService;

import lombok.extern.slf4j.Slf4j;

/** @author Andrei Yahorau */
@Service
@Slf4j
public class DogServiceImpl implements DogService {

  private final DogRepository dogRepository;

  @Autowired
  public DogServiceImpl(DogRepository dogRepository) {
    this.dogRepository = dogRepository;
  }

  @Override
  public DogResponse save(final DogRequest dogRequest) {
    log.info("Service. Save dog: {}", dogRequest);

    return dogRepository.save(dogRequest);
  }

  @Override
  public List<DogResponse> getAll() {
    log.info("Service. Get all dogs");

    return dogRepository.getAll();
  }

  @Override
  public void deleteById(final long id) {
    log.info("Service. Delete dog by id: {}", id);

    dogRepository.deleteById(id);
  }
}
