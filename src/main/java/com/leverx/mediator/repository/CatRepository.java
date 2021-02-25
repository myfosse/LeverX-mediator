package com.leverx.mediator.repository;

import java.util.List;

import com.leverx.mediator.dto.response.CatResponseDto;

/** @author Andrei Yahorau */
public interface CatRepository {

  CatResponseDto save();

  List<CatResponseDto> getAll();
}
