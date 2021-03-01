package com.leverx.mediator.repository.header;

import static lombok.AccessLevel.PRIVATE;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import com.leverx.mediator.model.auth.Auth;

import lombok.NoArgsConstructor;

/** @author Andrei Yahorau */
@NoArgsConstructor(access = PRIVATE)
public final class EntityHeaderCreation {

  public static HttpEntity<?> createEntityHeaderWithoutBody(final String auth) {
    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", auth);
    return new HttpEntity<>(headers);
  }

  public static <T> HttpEntity<T> createEntityHeaderWithBody(final T entity, final Auth auth) {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Authorization", auth.getAuth());
    return new HttpEntity<>(entity, headers);
  }
}
