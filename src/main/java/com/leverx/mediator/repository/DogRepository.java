package com.leverx.mediator.repository;

import java.util.List;

import com.leverx.mediator.dto.request.DogRequest;
import com.leverx.mediator.dto.response.DogResponse;

/** @author Andrei Yahorau */
public interface DogRepository {

  DogResponse save(final DogRequest dog);

  List<DogResponse> getAll();

  void deleteById(final long id);
}
