package com.leverx.mediator.repository.destination.impl;

import static com.leverx.mediator.dto.converter.CatConverter.convertStringToCatResponse;
import static com.leverx.mediator.dto.converter.CatConverter.convertStringToListOfCatResponse;
import static com.leverx.mediator.repository.destination.constant.DestinationConstants.CHARSET_UTF_8;
import static com.leverx.mediator.repository.destination.constant.DestinationConstants.ENDPOINT_CATS;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.util.EntityUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.leverx.mediator.dto.request.CatRequest;
import com.leverx.mediator.dto.response.CatResponse;
import com.leverx.mediator.exception.RepositoryException;
import com.leverx.mediator.repository.CatRepository;
import com.leverx.mediator.repository.destination.creator.DestinationCreator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/** @author Andrei Yahorau */
@Repository
@Primary
@RequiredArgsConstructor
@Slf4j
public class CatDestinationRepositoryImpl implements CatRepository {

  private final DestinationCreator destinationCreator;

  @Override
  public CatResponse save(final CatRequest cat) {
    log.info("CatRepository. Save cat: {}", cat);

    CatResponse catResponse;

    try {

      HttpResponse httpResponse = destinationCreator.executeHttpPost(ENDPOINT_CATS, cat);
      String responseString = EntityUtils.toString(httpResponse.getEntity(), CHARSET_UTF_8);
      catResponse = convertStringToCatResponse(responseString);

    } catch (final UnsupportedEncodingException e) {
      log.error("CatRepository. Unsupported encoding format while convert CatResponse");
      throw new RepositoryException("Unsupported encoding format while convert CatResponse");
    } catch (final JsonProcessingException | ClientProtocolException e) {
      log.error("CatRepository. Can't convert to CatResponse");
      throw new RepositoryException("Can't convert to CatResponse");
    } catch (final IOException e) {
      log.error("CatRepository. Can't execute HttpPost");
      throw new RepositoryException("Can't execute HttpPost");
    }

    return catResponse;
  }

  @Override
  public List<CatResponse> getAll() {
    log.info("CatRepository. Get all");

    List<CatResponse> catResponseList;

    try {

      HttpResponse httpResponse = destinationCreator.executeHttpGet(ENDPOINT_CATS);
      String responseString = EntityUtils.toString(httpResponse.getEntity(), CHARSET_UTF_8);
      catResponseList = convertStringToListOfCatResponse(responseString);

    } catch (final JsonProcessingException | ClientProtocolException e) {
      log.error("CatRepository. Can't convert to CatResponse");
      throw new RepositoryException("Can't convert to CatResponse");
    } catch (final IOException e) {
      log.error("CatRepository. Can't execute HttpPost");
      throw new RepositoryException("Can't execute HttpPost");
    }

    return catResponseList;
  }

  @Override
  public void deleteById(final long id) {
    log.info("CatRepository. Delete cat by id: {}", id);

    String url = ENDPOINT_CATS + "/" + id;

    try {
      destinationCreator.executeHttpDelete(url);
    } catch (final IOException e) {
      log.error("CatRepository. Can't execute HttpDelete");
      throw new RepositoryException("Can't execute HttpDelete");
    }
  }
}
