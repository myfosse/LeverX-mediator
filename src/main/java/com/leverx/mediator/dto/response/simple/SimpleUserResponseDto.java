package com.leverx.mediator.dto.response.simple;

import java.time.LocalDate;

import com.leverx.mediator.model.ERole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author Andrei Yahorau */
@Data
@Builder(builderMethodName = "simpleUserResponseBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class SimpleUserResponseDto {

  private long id;

  private String firstName;

  private String lastName;

  private String email;

  private LocalDate birthdate;

  private ERole role;
}
