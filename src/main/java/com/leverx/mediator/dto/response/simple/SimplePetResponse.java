package com.leverx.mediator.dto.response.simple;

import java.time.LocalDate;

import com.leverx.mediator.model.EPetType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author Andrei Yahorau */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimplePetResponse {

  private long id;

  private EPetType petType;

  private String name;

  private LocalDate birthdate;
}
