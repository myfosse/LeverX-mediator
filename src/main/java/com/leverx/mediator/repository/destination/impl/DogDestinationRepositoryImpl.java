package com.leverx.mediator.repository.destination.impl;

import static com.leverx.mediator.dto.converter.DogConverter.convertStringToDogResponse;
import static com.leverx.mediator.dto.converter.DogConverter.convertStringToListOfDogResponse;
import static com.leverx.mediator.repository.destination.constant.DestinationConstants.CHARSET_UTF_8;
import static com.leverx.mediator.repository.destination.constant.DestinationConstants.ENDPOINT_DOGS;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.util.EntityUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.leverx.mediator.dto.request.DogRequest;
import com.leverx.mediator.dto.response.DogResponse;
import com.leverx.mediator.exception.RepositoryException;
import com.leverx.mediator.repository.DogRepository;
import com.leverx.mediator.repository.destination.creator.DestinationCreator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/** @author Andrei Yahorau */
@Repository
@Primary
@RequiredArgsConstructor
@Slf4j
public class DogDestinationRepositoryImpl implements DogRepository {

  private final DestinationCreator destinationCreator;

  @Override
  public DogResponse save(final DogRequest dog) {
    log.info("DogRepository. Save dog: {}", dog);

    DogResponse dogResponse;

    try {

      HttpResponse httpResponse = destinationCreator.executeHttpPost(ENDPOINT_DOGS, dog);
      String responseString = EntityUtils.toString(httpResponse.getEntity(), CHARSET_UTF_8);
      dogResponse = convertStringToDogResponse(responseString);

    } catch (final UnsupportedEncodingException e) {
      log.error("DogRepository. Unsupported encoding format while convert DogResponse");
      throw new RepositoryException("Unsupported encoding format while convert DogResponse");
    } catch (final JsonProcessingException | ClientProtocolException e) {
      log.error("DogRepository. Can't convert to DogResponse");
      throw new RepositoryException("Can't convert to DogResponse");
    } catch (final IOException e) {
      log.error("DogRepository. Can't execute HttpPost");
      throw new RepositoryException("Can't execute HttpPost");
    }

    return dogResponse;
  }

  @Override
  public List<DogResponse> getAll() {
    log.info("DogRepository. Get all");

    List<DogResponse> dogResponseList;

    try {

      HttpResponse httpResponse = destinationCreator.executeHttpGet(ENDPOINT_DOGS);
      String responseString = EntityUtils.toString(httpResponse.getEntity(), CHARSET_UTF_8);
      dogResponseList = convertStringToListOfDogResponse(responseString);

    } catch (final JsonProcessingException | ClientProtocolException e) {
      log.error("DogRepository. Can't convert to DogResponse");
      throw new RepositoryException("Can't convert to DogResponse");
    } catch (final IOException e) {
      log.error("DogRepository. Can't execute HttpPost");
      throw new RepositoryException("Can't execute HttpPost");
    }

    return dogResponseList;
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
