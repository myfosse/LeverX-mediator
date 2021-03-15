package com.leverx.mediator.repository.resttemplate.impl;

import static java.util.Arrays.asList;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

import static com.leverx.mediator.repository.resttemplate.header.EntityHeaderCreation.createEntityHeaderWithBody;
import static com.leverx.mediator.repository.resttemplate.header.EntityHeaderCreation.createEntityHeaderWithoutBody;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.leverx.mediator.dto.request.UserRequest;
import com.leverx.mediator.dto.response.UserResponse;
import com.leverx.mediator.model.auth.Auth;
import com.leverx.mediator.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

/** @author Andrei Yahorau */
@Repository
@Slf4j
@Profile("resttemplate")
public class UserRestTemplateRepositoryImpl implements UserRepository {

  private final String usersLink;

  private final Auth auth;

  private final RestTemplate restTemplate;

  @Autowired
  public UserRestTemplateRepositoryImpl(
      final RestTemplateBuilder builder,
      final Auth auth,
      @Value("${sap.link.users}") final String usersLink) {
    this.restTemplate = builder.build();
    this.auth = auth;
    this.usersLink = usersLink;
  }

  @Override
  public UserResponse save(final UserRequest userRequest) {
    log.info("Repository. Save user: {}", userRequest);

    ResponseEntity<UserResponse> userResponseEntity =
        restTemplate.exchange(
            usersLink,
            POST,
            createEntityHeaderWithBody(userRequest, auth.getAuth()),
            UserResponse.class);

    return userResponseEntity.getBody();
  }

  @Override
  public List<UserResponse> getAll() {
    log.info("Repository. Get all users");

    ResponseEntity<UserResponse[]> userResponseEntity =
        restTemplate.exchange(
            usersLink, GET, createEntityHeaderWithoutBody(auth.getAuth()), UserResponse[].class);

    return asList(userResponseEntity.getBody());
  }

  @Override
  public void deleteById(final long id) {
    log.info("Repository. Delete user by id: {}", id);

    restTemplate.exchange(
        usersLink + "/" + id, DELETE, createEntityHeaderWithoutBody(auth.getAuth()), Object.class);
  }
}
