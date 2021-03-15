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

import com.leverx.mediator.dto.request.DogRequest;
import com.leverx.mediator.dto.response.DogResponse;
import com.leverx.mediator.model.auth.Auth;
import com.leverx.mediator.repository.DogRepository;

import lombok.extern.slf4j.Slf4j;

/** @author Andrei Yahorau */
@Repository
@Slf4j
@Profile("resttemplate")
public class DogRestTemplateRepositoryImpl implements DogRepository {

  private final String dogsLink;

  private final Auth auth;

  private final RestTemplate restTemplate;

  @Autowired
  public DogRestTemplateRepositoryImpl(
      final RestTemplateBuilder builder,
      final Auth auth,
      @Value("${sap.link.dogs}") final String dogsLink) {
    this.restTemplate = builder.build();
    this.auth = auth;
    this.dogsLink = dogsLink;
  }

  @Override
  public DogResponse save(final DogRequest dogRequest) {
    log.info("Repository. Save dog: {}", dogRequest);

    ResponseEntity<DogResponse> dogResponseEntity =
        restTemplate.exchange(
            dogsLink,
            POST,
            createEntityHeaderWithBody(dogRequest, auth.getAuth()),
            DogResponse.class);

    return dogResponseEntity.getBody();
  }

  @Override
  public List<DogResponse> getAll() {
    log.info("Repository. Get all dogs");

    ResponseEntity<DogResponse[]> dogResponseEntity =
        restTemplate.exchange(
            dogsLink, GET, createEntityHeaderWithoutBody(auth.getAuth()), DogResponse[].class);

    return asList(dogResponseEntity.getBody());
  }

  @Override
  public void deleteById(final long id) {
    log.info("Repository. Delete dog by id: {}", id);

    restTemplate.exchange(
        dogsLink + "/" + id, DELETE, createEntityHeaderWithoutBody(auth.getAuth()), Object.class);
  }
}
