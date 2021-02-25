package com.leverx.mediator.service;

import java.util.List;

import com.leverx.mediator.dto.request.CatRequestDto;
import com.leverx.mediator.dto.response.CatResponseDto;

/** @author Andrei Yahorau */
public interface CatService {

  CatResponseDto save(final CatRequestDto catRequestDto);

  List<CatRequestDto> getAll();
}
