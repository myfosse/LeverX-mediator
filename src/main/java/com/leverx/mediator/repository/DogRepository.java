package com.leverx.mediator.repository;

import java.util.List;
import java.util.Optional;

import com.leverx.mediator.dto.request.DogRequest;
import com.leverx.mediator.dto.response.DogResponse;

/** @author Andrei Yahorau */
public interface DogRepository {

  Optional<DogResponse> save(final DogRequest dogRequest);

  List<DogResponse> getAll();

  void deleteById(final long id);
}
