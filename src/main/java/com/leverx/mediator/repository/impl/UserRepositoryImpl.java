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

import com.leverx.mediator.dto.response.UserResponseDto;
import com.leverx.mediator.model.auth.Auth;
import com.leverx.mediator.repository.UserRepository;
import com.leverx.mediator.repository.header.EntityHeaderCreation;

import lombok.extern.slf4j.Slf4j;

/** @author Andrei Yahorau */
@Repository
@Slf4j
public class UserRepositoryImpl implements UserRepository {

  @Value("${leverx.com.link.users}")
  private String usersLink;

  private final Auth auth;

  private final RestTemplate restTemplate = new RestTemplate();

  @Autowired
  public UserRepositoryImpl(final Auth auth) {
    this.auth = auth;
  }

  @Override
  public UserResponseDto save() {
    return null;
  }

  @Override
  public List<UserResponseDto> getAll() {
    log.info("Get all users from another server");

    return Arrays.asList(Objects.requireNonNull(restTemplate
        .exchange(usersLink, GET, createEntityHeader(auth.getAuth()), UserResponseDto[].class)
        .getBody()));
  }
}
