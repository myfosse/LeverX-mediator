package com.leverx.mediator.repository;

import java.util.List;

import com.leverx.mediator.dto.response.DogResponseDto;

/** @author Andrei Yahorau */
public interface DogRepository {

  DogResponseDto save();

  List<DogResponseDto> getAll();
}
