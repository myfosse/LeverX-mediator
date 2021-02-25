package com.leverx.mediator.dto.response.every;

import com.leverx.mediator.dto.response.CatResponseDto;
import com.leverx.mediator.dto.response.DogResponseDto;
import com.leverx.mediator.dto.response.UserResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author Andrei Yahorau */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EverySingleResponse {

  private CatResponseDto cat;

  private DogResponseDto dog;

  private UserResponseDto user;
}
