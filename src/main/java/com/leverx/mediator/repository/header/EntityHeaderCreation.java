package com.leverx.mediator.repository.header;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

/** @author Andrei Yahorau */
public class EntityHeaderCreation<T> {

  public HttpEntity<T> createEntityHeader(final String auth) {
    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", auth);
    return new HttpEntity<>(headers);
  }
}
