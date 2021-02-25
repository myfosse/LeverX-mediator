package com.leverx.mediator.repository;

import static org.springframework.http.HttpMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.leverx.mediator.dto.response.CatResponseDto;
import com.leverx.mediator.model.auth.Auth;
import com.leverx.mediator.repository.header.EntityHeaderCreation;

import lombok.extern.slf4j.Slf4j;

/** @author Andrei Yahorau */
@Repository
@Slf4j
public class CatRepository extends EntityHeaderCreation<CatResponseDto> {

  @Value("${leverx.com.link.cats}")
  private String catsLink;

  private final Auth auth;

  @Autowired
  public CatRepository(final Auth auth) {
    this.auth = auth;
  }

  public CatResponseDto[] getAllCats() {
    log.info("Get all dogs from another server");

    return new RestTemplate()
        .exchange(catsLink, GET, createEntityHeader(auth.getAuth()), CatResponseDto[].class)
        .getBody();
  }
}
