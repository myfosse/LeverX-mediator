package com.leverx.mediator.repository;

import static org.springframework.http.HttpMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.leverx.mediator.dto.response.UserResponseDto;
import com.leverx.mediator.model.auth.Auth;
import com.leverx.mediator.repository.header.EntityHeaderCreation;

import lombok.extern.slf4j.Slf4j;

/** @author Andrei Yahorau */
@Repository
@Slf4j
public class UserRepository extends EntityHeaderCreation<UserResponseDto> {

  @Value("${leverx.com.link.users}")
  private String usersLink;

  private final Auth auth;

  @Autowired
  public UserRepository(final Auth auth) {
    this.auth = auth;
  }

  public UserResponseDto[] getAllUsers() {
    log.info("Get all users from another server");

    return new RestTemplate()
        .exchange(usersLink, GET, createEntityHeader(auth.getAuth()), UserResponseDto[].class)
        .getBody();
  }
}
