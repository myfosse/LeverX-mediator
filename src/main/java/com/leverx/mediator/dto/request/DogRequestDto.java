package com.leverx.mediator.dto.request;

import javax.validation.constraints.NotNull;

import lombok.Data;

/** @author Andrei Yahorau */
@Data
public class DogRequestDto extends PetRequestDto {

  @NotNull
  private boolean isGuideDog;
}
