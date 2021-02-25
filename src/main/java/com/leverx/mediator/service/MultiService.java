package com.leverx.mediator.service;

import com.leverx.mediator.dto.request.CatRequestDto;
import com.leverx.mediator.dto.request.DogRequestDto;
import com.leverx.mediator.dto.request.UserRequestDto;
import com.leverx.mediator.dto.response.every.EveryListResponse;
import com.leverx.mediator.dto.response.every.EverySingleResponse;

/** @author Andrei Yahorau */
public interface MultiService {

  EverySingleResponse save(
      final CatRequestDto catRequestDto,
      final DogRequestDto dogRequestDto,
      final UserRequestDto userRequestDto);

  EveryListResponse getAllLists();
}
