package com.leverx.mediator.dto.response.simple;

import java.time.LocalDate;

import com.leverx.mediator.model.EPetType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author Andrei Yahorau */
@Data
@Builder(builderMethodName = "simplePetResponseBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class SimplePetResponseDto {

  private long id;

  private EPetType petType;

  private String name;

  private LocalDate birthdate;
}
