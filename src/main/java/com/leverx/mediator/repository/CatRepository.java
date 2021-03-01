package com.leverx.mediator.repository;

import java.util.List;
import java.util.Optional;

import com.leverx.mediator.dto.request.CatRequest;
import com.leverx.mediator.dto.response.CatResponse;

/** @author Andrei Yahorau */
public interface CatRepository {

  Optional<CatResponse> save(final CatRequest catRequest);

  List<CatResponse> getAll();

  void deleteById(final long id);
}
