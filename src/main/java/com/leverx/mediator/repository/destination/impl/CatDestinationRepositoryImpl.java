package com.leverx.mediator.repository.destination.impl;

import static java.nio.charset.StandardCharsets.UTF_8;

import static com.leverx.mediator.converter.ObjectConverter.convertJsonStringToListOfObjects;
import static com.leverx.mediator.converter.ObjectConverter.convertJsonStringToObject;
import static com.leverx.mediator.converter.ObjectConverter.convertObjectToJsonString;
import static com.leverx.mediator.repository.destination.constant.DestinationConstants.PATH_CATS;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.leverx.mediator.dto.request.CatRequest;
import com.leverx.mediator.dto.response.CatResponse;
import com.leverx.mediator.exception.RepositoryException;
import com.leverx.mediator.repository.CatRepository;
import com.leverx.mediator.repository.destination.creator.DestinationCreator;

import lombok.extern.slf4j.Slf4j;

/** @author Andrei Yahorau */
@Slf4j
@Profile("destination")
@Repository
public class CatDestinationRepositoryImpl implements CatRepository {

  private final DestinationCreator destinationCreator;

  @Autowired
  public CatDestinationRepositoryImpl(final DestinationCreator destinationCreator) {
    this.destinationCreator = destinationCreator;
  }

  @Override
  public CatResponse save(final CatRequest cat) {
    log.info("CatRepository. Save cat: {}", cat);

    try {

      HttpResponse httpResponse =
          destinationCreator.executeHttpPost(PATH_CATS, convertObjectToJsonString(cat));
      String responseString = EntityUtils.toString(httpResponse.getEntity(), UTF_8);
      return convertJsonStringToObject(responseString, CatResponse.class);

    } catch (final UnsupportedEncodingException e) {
      log.error("CatRepository. Unsupported encoding format while convert CatResponse");
      throw new RepositoryException("Unsupported encoding format while convert CatResponse");

    } catch (final JsonProcessingException e) {
      log.error("CatRepository. Can't convert to CatResponse");
      throw new RepositoryException("Can't convert to CatResponse");

    } catch (final IOException e) {
      log.error("CatRepository. Can't execute HttpPost");
      throw new RepositoryException("Can't execute HttpPost");
    }
  }

  @Override
  public List<CatResponse> getAll() {
    log.info("CatRepository. Get all");

    try {

      HttpResponse httpResponse = destinationCreator.executeHttpGet(PATH_CATS);
      String responseString = EntityUtils.toString(httpResponse.getEntity(), UTF_8);
      return convertJsonStringToListOfObjects(responseString, CatResponse[].class);

    } catch (final JsonProcessingException e) {
      log.error("CatRepository. Can't convert to CatResponse");
      throw new RepositoryException("Can't convert to CatResponse");

    } catch (final IOException e) {
      log.error("CatRepository. Can't execute HttpPost");
      throw new RepositoryException("Can't execute HttpPost");
    }
  }

  @Override
  public void deleteById(final long id) {
    log.info("CatRepository. Delete cat by id: {}", id);

    String url = PATH_CATS + "/" + id;

    try {

      destinationCreator.executeHttpDelete(url);

    } catch (final IOException e) {
      log.error("CatRepository. Can't execute HttpDelete");
      throw new RepositoryException("Can't execute HttpDelete");
    }
  }
}
