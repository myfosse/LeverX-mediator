package com.leverx.mediator.dto.request;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/** @author Andrei Yahorau */
@Data
public class PetRequest {

  @NotNull
  private String name;

  @PastOrPresent
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate birthdate;

  @Positive
  private long ownerId;
}
