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

import com.leverx.mediator.dto.request.CatRequest;
import com.leverx.mediator.dto.response.CatResponse;
import com.leverx.mediator.model.auth.Auth;
import com.leverx.mediator.repository.CatRepository;

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
  public Optional<CatResponse> save(final CatRequest catRequest) {
    log.info("Repository. Save cat: {}", catRequest);

    ResponseEntity<CatResponse> catResponseEntity = restTemplate.exchange(
        catsLink,
        POST,
        createEntityHeaderWithBody(catRequest, auth),
        CatResponse.class);

    return ofNullable(catResponseEntity.getBody());
  }

  @Override
  public List<CatResponse> getAll() {
    log.info("Repository. Get all cats");

    ResponseEntity<CatResponse[]> catResponseEntity = restTemplate.exchange(
        catsLink,
        GET,
        createEntityHeaderWithoutBody(auth.getAuth()),
        CatResponse[].class);

    return asList(ofNullable(catResponseEntity.getBody())
        .orElseThrow(() -> new HttpClientErrorException(BAD_REQUEST)));
  }

  @Override
  public void deleteById(final long id) {
    log.info("Repository. Delete cat by id: {}", id);

    restTemplate.exchange(
        catsLink + "/" + id,
        DELETE,
        createEntityHeaderWithoutBody(auth.getAuth()),
        Object.class);
  }
}
