package com.leverx.mediator.repository.destination.impl;

import static java.nio.charset.StandardCharsets.UTF_8;

import static com.leverx.mediator.dto.converter.DogConverter.convertDogToJsonString;
import static com.leverx.mediator.dto.converter.DogConverter.convertStringToDogResponse;
import static com.leverx.mediator.dto.converter.DogConverter.convertStringToListOfDogResponse;
import static com.leverx.mediator.repository.destination.constant.DestinationConstants.ENDPOINT_DOGS;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.leverx.mediator.dto.request.DogRequest;
import com.leverx.mediator.dto.response.DogResponse;
import com.leverx.mediator.exception.RepositoryException;
import com.leverx.mediator.repository.DogRepository;
import com.leverx.mediator.repository.destination.creator.DestinationCreator;

import lombok.extern.slf4j.Slf4j;

/** @author Andrei Yahorau */
@Slf4j
@Profile("destination")
@Repository
public class DogDestinationRepositoryImpl implements DogRepository {

  private final DestinationCreator destinationCreator;

  @Autowired
  public DogDestinationRepositoryImpl(final DestinationCreator destinationCreator) {
    this.destinationCreator = destinationCreator;
  }

  @Override
  public DogResponse save(final DogRequest dog) {
    log.info("DogRepository. Save dog: {}", dog);

    try {

      HttpResponse httpResponse =
          destinationCreator.executeHttpPost(ENDPOINT_DOGS, convertDogToJsonString(dog));
      String responseString = EntityUtils.toString(httpResponse.getEntity(), UTF_8);
      return convertStringToDogResponse(responseString);

    } catch (final UnsupportedEncodingException e) {
      log.error("DogRepository. Unsupported encoding format while convert DogResponse");
      throw new RepositoryException("Unsupported encoding format while convert DogResponse");

    } catch (final JsonProcessingException e) {
      log.error("DogRepository. Can't convert to DogResponse");
      throw new RepositoryException("Can't convert to DogResponse");

    } catch (final IOException e) {
      log.error("DogRepository. Can't execute HttpPost");
      throw new RepositoryException("Can't execute HttpPost");
    }
  }

  @Override
  public List<DogResponse> getAll() {
    log.info("DogRepository. Get all");

    try {

      HttpResponse httpResponse = destinationCreator.executeHttpGet(ENDPOINT_DOGS);
      String responseString = EntityUtils.toString(httpResponse.getEntity(), UTF_8);
      return convertStringToListOfDogResponse(responseString);

    } catch (final JsonProcessingException e) {
      log.error("DogRepository. Can't convert to DogResponse");
      throw new RepositoryException("Can't convert to DogResponse");

    } catch (final IOException e) {
      log.error("DogRepository. Can't execute HttpPost");
      throw new RepositoryException("Can't execute HttpPost");
    }
  }

  @Override
  public void deleteById(final long id) {
    log.info("DogRepository. Delete dog by id: {}", id);

    String url = ENDPOINT_DOGS + "/" + id;

    try {

      destinationCreator.executeHttpDelete(url);

    } catch (final IOException e) {
      log.error("DogRepository. Can't execute HttpDelete");
      throw new RepositoryException("Can't execute HttpDelete");
    }
  }
}
