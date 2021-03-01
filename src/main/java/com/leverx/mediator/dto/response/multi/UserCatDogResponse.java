package com.leverx.mediator.dto.response.multi;

import com.leverx.mediator.dto.response.CatResponse;
import com.leverx.mediator.dto.response.DogResponse;
import com.leverx.mediator.dto.response.UserResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author Andrei Yahorau */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCatDogResponse {

  private CatResponse cat;

  private DogResponse dog;

  private UserResponse user;
}
