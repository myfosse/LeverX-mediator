package com.leverx.mediator.repository;

import java.util.List;

import com.leverx.mediator.dto.request.CatRequest;
import com.leverx.mediator.dto.response.CatResponse;

/** @author Andrei Yahorau */
public interface CatRepository {

  CatResponse save(final CatRequest catRequest);

  List<CatResponse> getAll();

  void deleteById(final long id);
}
