package com.leverx.mediator.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leverx.mediator.dto.request.CatRequest;
import com.leverx.mediator.dto.response.CatResponse;
import com.leverx.mediator.repository.CatRepository;
import com.leverx.mediator.service.CatService;

import lombok.extern.slf4j.Slf4j;

/** @author Andrei Yahorau */
@Service
@Slf4j
public class CatServiceImpl implements CatService {

  private final CatRepository catRepository;

  @Autowired
  public CatServiceImpl(final CatRepository catRepository) {
    this.catRepository = catRepository;
  }

  @Override
  public CatResponse save(final CatRequest catRequest) {
    log.info("Service. Save cat: {}", catRequest);

    return catRepository.save(catRequest);
  }

  @Override
  public List<CatResponse> getAll() {
    log.info("Service. Get all cats");

    return catRepository.getAll();
  }

  @Override
  public void deleteById(final long id) {
    log.info("Service. Delete cat by id: {}", id);

    catRepository.deleteById(id);
  }
}
