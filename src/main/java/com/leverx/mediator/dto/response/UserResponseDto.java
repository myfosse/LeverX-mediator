package com.leverx.mediator.dto.response;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.leverx.mediator.dto.response.simple.SimplePetResponseDto;
import com.leverx.mediator.model.ERole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author Andrei Yahorau */
@Data
@Builder(builderMethodName = "userResponseBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

  private long id;

  private String firstName;

  private String lastName;

  private String email;

  private LocalDate birthdate;

  private List<SimplePetResponseDto> pets;

  private ERole role;
}
