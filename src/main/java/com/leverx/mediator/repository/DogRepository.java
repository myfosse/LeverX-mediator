package com.leverx.mediator.repository;

import static org.springframework.http.HttpMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.leverx.mediator.dto.response.DogResponseDto;
import com.leverx.mediator.model.auth.Auth;
import com.leverx.mediator.repository.header.EntityHeaderCreation;

import lombok.extern.slf4j.Slf4j;

/** @author Andrei Yahorau */
@Repository
@Slf4j
public class DogRepository extends EntityHeaderCreation<DogResponseDto> {

  @Value("${leverx.com.link.dogs}")
  private String dogsLink;

  private final Auth auth;

  @Autowired
  public DogRepository(final Auth auth) {
    this.auth = auth;
  }

  public DogResponseDto[] getAllDogs() {
    log.info("Get all dogs from another server");

    return new RestTemplate()
        .exchange(dogsLink, GET, createEntityHeader(auth.getAuth()), DogResponseDto[].class)
        .getBody();
  }
}
