package com.leverx.mediator.dto.request;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;

/** @author Andrei Yahorau */
public class PetRequestDto {

  @NotNull
  private String name;

  @PastOrPresent
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate birthdate;

  @Positive
  private long ownerId;
}
