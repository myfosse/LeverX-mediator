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

import com.leverx.mediator.dto.request.DogRequest;
import com.leverx.mediator.dto.response.DogResponse;
import com.leverx.mediator.model.auth.Auth;
import com.leverx.mediator.repository.DogRepository;

import lombok.extern.slf4j.Slf4j;

/** @author Andrei Yahorau */
@Repository
@Slf4j
public class DogRepositoryImpl implements DogRepository {

  @Value("${leverx.com.link.dogs}")
  private String dogsLink;

  private final Auth auth;

  private final RestTemplate restTemplate = new RestTemplate();

  @Autowired
  public DogRepositoryImpl(final Auth auth) {
    this.auth = auth;
  }

  @Override
  public Optional<DogResponse> save(final DogRequest dogRequest) {
    log.info("Repository. Save dog: {}", dogRequest);

    ResponseEntity<DogResponse> dogResponseEntity = restTemplate.exchange(
        dogsLink,
        POST,
        createEntityHeaderWithBody(dogRequest, auth),
        DogResponse.class);

    return ofNullable(dogResponseEntity.getBody());
  }

  @Override
  public List<DogResponse> getAll() {
    log.info("Repository. Get all dogs");

    ResponseEntity<DogResponse[]> dogResponseEntity = restTemplate.exchange(
        dogsLink,
        GET,
        createEntityHeaderWithoutBody(auth.getAuth()),
        DogResponse[].class);

    return asList(ofNullable(dogResponseEntity.getBody())
        .orElseThrow(() -> new HttpClientErrorException(BAD_REQUEST)));
  }

  @Override
  public void deleteById(final long id) {
    log.info("Repository. Delete dog by id: {}", id);

    restTemplate.exchange(
        dogsLink + "/" + id,
        DELETE,
        createEntityHeaderWithoutBody(auth.getAuth()),
        Object.class);
  }
}
