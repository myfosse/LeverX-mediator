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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.leverx.mediator.dto.request.CatRequest;
import com.leverx.mediator.dto.response.CatResponse;
import com.leverx.mediator.model.auth.Auth;
import com.leverx.mediator.repository.CatRepository;

import lombok.extern.slf4j.Slf4j;

/** @author Andrei Yahorau */
@Repository
@Slf4j
public class CatRestTemplateRepositoryImpl implements CatRepository {

  private final String catsLink;

  private final Auth auth;

  private final RestTemplate restTemplate;

  @Autowired
  public CatRestTemplateRepositoryImpl(
      final RestTemplateBuilder builder,
      final Auth auth,
      @Value("${sap.link.cats}") final String catsLink) {
    this.restTemplate = builder.build();
    this.auth = auth;
    this.catsLink = catsLink;
  }

  @Override
  public CatResponse save(final CatRequest catRequest) {
    log.info("Repository. Save cat: {}", catRequest);

    ResponseEntity<CatResponse> catResponseEntity =
        restTemplate.exchange(
            catsLink,
            POST,
            createEntityHeaderWithBody(catRequest, auth.getAuth()),
            CatResponse.class);

    return catResponseEntity.getBody();
  }

  @Override
  public List<CatResponse> getAll() {
    log.info("Repository. Get all cats");

    ResponseEntity<CatResponse[]> catResponseEntity =
        restTemplate.exchange(
            catsLink, GET, createEntityHeaderWithoutBody(auth.getAuth()), CatResponse[].class);

    return asList(catResponseEntity.getBody());
  }

  @Override
  public void deleteById(final long id) {
    log.info("Repository. Delete cat by id: {}", id);

    restTemplate.exchange(
        catsLink + "/" + id, DELETE, createEntityHeaderWithoutBody(auth.getAuth()), Object.class);
  }
}
