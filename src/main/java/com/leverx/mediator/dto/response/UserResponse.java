package com.leverx.mediator.dto.response;

import java.time.LocalDate;
import java.util.List;

import com.leverx.mediator.dto.response.simple.SimplePetResponse;
import com.leverx.mediator.model.ERole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author Andrei Yahorau */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

  private long id;

  private String firstName;

  private String lastName;

  private String email;

  private LocalDate birthdate;

  private List<SimplePetResponse> pets;

  private ERole role;
}
