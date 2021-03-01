package com.leverx.mediator.dto.request.multi;

import com.leverx.mediator.dto.request.CatRequest;
import com.leverx.mediator.dto.request.DogRequest;
import com.leverx.mediator.dto.request.UserRequest;

import lombok.Data;

/** @author Andrei Yahorau */
@Data
public class UserCatDogRequest {

  private UserRequest user;

  private CatRequest cat;

  private DogRequest dog;
}
