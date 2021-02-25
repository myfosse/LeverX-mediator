package com.leverx.mediator.service;

import java.util.List;

import com.leverx.mediator.dto.request.DogRequestDto;
import com.leverx.mediator.dto.response.DogResponseDto;

/** @author Andrei Yahorau */
public interface DogService {

  DogResponseDto save(final DogRequestDto catRequestDto);

  List<DogRequestDto> getAll();
}
