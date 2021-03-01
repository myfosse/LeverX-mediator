package com.leverx.mediator.service;

import java.util.List;

import com.leverx.mediator.dto.request.DogRequest;
import com.leverx.mediator.dto.response.DogResponse;

/** @author Andrei Yahorau */
public interface DogService {

  DogResponse save(final DogRequest dogRequest);

  List<DogResponse> getAll();

  void deleteById(final long id);
}
