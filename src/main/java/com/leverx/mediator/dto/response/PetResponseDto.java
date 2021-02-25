package com.leverx.mediator.dto.response;

import java.time.LocalDate;

import com.leverx.mediator.dto.response.simple.SimpleUserResponseDto;
import com.leverx.mediator.model.EPetType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author Andrei Yahorau */
@Data
@Builder(builderMethodName = "petResponseBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class PetResponseDto {

  private long id;

  private EPetType petType;

  private String name;

  private LocalDate birthdate;

  private SimpleUserResponseDto owner;
}
