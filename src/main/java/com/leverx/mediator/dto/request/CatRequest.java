package com.leverx.mediator.dto.request;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.ToString;

/** @author Andrei Yahorau */
@Data
@ToString(callSuper = true)
public class CatRequest extends PetRequest {

  @NotNull
  private boolean isBold;
}
