package com.leverx.mediator.service;

import com.leverx.mediator.dto.request.multi.UserCatDogRequest;
import com.leverx.mediator.dto.response.multi.UserCatDogListResponse;
import com.leverx.mediator.dto.response.multi.UserCatDogResponse;

/** @author Andrei Yahorau */
public interface MultiService {

  UserCatDogResponse save(final UserCatDogRequest userCatDogRequest);

  UserCatDogListResponse getAllUsersAndPets();
}
