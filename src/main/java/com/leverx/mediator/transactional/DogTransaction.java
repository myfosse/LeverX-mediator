package com.leverx.mediator.transactional;

import com.leverx.mediator.dto.request.DogRequest;
import com.leverx.mediator.dto.response.DogResponse;

/** @author Andrei Yahorau */
public interface DogTransaction extends Transaction<DogResponse, DogRequest>{

  DogResponse executeSave(final DogRequest dog);

  void rollback();
}
