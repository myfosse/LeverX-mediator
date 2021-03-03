package com.leverx.mediator.transactional;

import com.leverx.mediator.dto.request.UserRequest;
import com.leverx.mediator.dto.response.UserResponse;

/** @author Andrei Yahorau */
public interface UserTransaction extends Transaction<UserResponse, UserRequest> {

  UserResponse executeSave(final UserRequest user);

  void rollback();
}