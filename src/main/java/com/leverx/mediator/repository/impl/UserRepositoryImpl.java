package com.leverx.mediator.repository.impl;

import static java.util.Arrays.asList;
import static java.util.Optional.ofNullable;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import static com.leverx.mediator.repository.header.EntityHeaderCreation.createEntityHeaderWithoutBody;
import static com.leverx.mediator.repository.header.EntityHeaderCreation.createEntityHeaderWithBody;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.leverx.mediator.dto.request.UserRequest;
import com.leverx.mediator.dto.response.UserResponse;
import com.leverx.mediator.model.auth.Auth;
import com.leverx.mediator.repository.UserRepository;

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
  public Optional<UserResponse> save(final UserRequest userRequest) {
    log.info("Repository. Save user: {}", userRequest);

    ResponseEntity<UserResponse> userResponseEntity = restTemplate.exchange(
        usersLink,
        POST,
        createEntityHeaderWithBody(userRequest, auth),
        UserResponse.class);

    return ofNullable(userResponseEntity.getBody());
  }

  @Override
  public List<UserResponse> getAll() {
    log.info("Repository. Get all users");

    ResponseEntity<UserResponse[]> userResponseEntity = restTemplate.exchange(
        usersLink,
        GET,
        createEntityHeaderWithoutBody(auth.getAuth()),
        UserResponse[].class);

    return asList(ofNullable(userResponseEntity.getBody())
        .orElseThrow(() -> new HttpClientErrorException(BAD_REQUEST)));
  }

  @Override
  public void deleteById(final long id) {
    log.info("Repository. Delete user by id: {}", id);

    restTemplate.exchange(
        usersLink + "/" + id,
        DELETE,
        createEntityHeaderWithoutBody(auth.getAuth()),
        Object.class);
  }
}
