package com.leverx.mediator.repository.impl;

import static org.springframework.http.HttpMethod.GET;

import static com.leverx.mediator.repository.header.EntityHeaderCreation.createEntityHeader;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.leverx.mediator.dto.response.CatResponseDto;
import com.leverx.mediator.model.auth.Auth;
import com.leverx.mediator.repository.CatRepository;
import com.leverx.mediator.repository.header.EntityHeaderCreation;

import lombok.extern.slf4j.Slf4j;

/** @author Andrei Yahorau */
@Repository
@Slf4j
public class CatRepositoryImpl implements CatRepository {

  @Value("${leverx.com.link.cats}")
  private String catsLink;

  private final Auth auth;

  private final RestTemplate restTemplate = new RestTemplate();

  @Autowired
  public CatRepositoryImpl(final Auth auth) {
    this.auth = auth;
  }

  @Override
  public CatResponseDto save() {
    return null;
  }

  @Override
  public List<CatResponseDto> getAll() {
    log.info("Get all dogs from another server");

    return Arrays.asList(Objects.requireNonNull(restTemplate
        .exchange(catsLink, GET, createEntityHeader(auth.getAuth()), CatResponseDto[].class)
        .getBody()));
  }
}
