package com.leverx.mediator.repository.header;

import static lombok.AccessLevel.PRIVATE;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import lombok.NoArgsConstructor;

/** @author Andrei Yahorau */
@NoArgsConstructor(access = PRIVATE)
public final class EntityHeaderCreation {

  public static HttpEntity<?> createEntityHeaderWithoutBody(final String auth) {
    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", auth);
    return new HttpEntity<>(headers);
  }

  public static <T> HttpEntity<T> createEntityHeaderWithBody(final T entity, final String auth) {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Authorization", auth);
    return new HttpEntity<>(entity, headers);
  }
}
