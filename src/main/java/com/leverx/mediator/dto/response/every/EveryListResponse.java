package com.leverx.mediator.dto.response.every;

import java.util.List;

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
public class EveryListResponse {

  private List<CatResponseDto> cats;

  private List<DogResponseDto> dogs;

  private List<UserResponseDto> users;
}
