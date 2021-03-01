package com.leverx.mediator.dto.response.multi;

import java.util.List;

import com.leverx.mediator.dto.response.CatResponse;
import com.leverx.mediator.dto.response.DogResponse;
import com.leverx.mediator.dto.response.UserResponse;

import lombok.Builder;
import lombok.Data;

/** @author Andrei Yahorau */
@Data
@Builder
public class UserCatDogListResponse {

  private List<CatResponse> cats;

  private List<DogResponse> dogs;

  private List<UserResponse> users;
}
