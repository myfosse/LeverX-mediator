package com.leverx.mediator.transactional;

import com.leverx.mediator.dto.request.multi.UserCatDogRequest;
import com.leverx.mediator.dto.response.multi.UserCatDogResponse;

/** @author Andrei Yahorau */
public interface UserCatDogTransaction {

  UserCatDogResponse executeSave(final UserCatDogRequest userCatDog);
}
