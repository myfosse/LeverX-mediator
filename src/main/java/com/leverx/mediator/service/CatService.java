package com.leverx.mediator.service;

import java.util.List;

import com.leverx.mediator.dto.request.CatRequest;
import com.leverx.mediator.dto.response.CatResponse;

/** @author Andrei Yahorau */
public interface CatService {

  CatResponse save(final CatRequest catRequest);

  List<CatResponse> getAll();

  void deleteById(final long id);
}
