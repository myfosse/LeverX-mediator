package com.leverx.mediator.transactional;

import com.leverx.mediator.dto.request.CatRequest;
import com.leverx.mediator.dto.response.CatResponse;

/** @author Andrei Yahorau */
public interface CatTransaction extends Transaction<CatResponse, CatRequest> {

  CatResponse executeSave(final CatRequest cat);

  void rollback();
}
