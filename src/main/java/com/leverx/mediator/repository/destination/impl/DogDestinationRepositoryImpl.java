package com.leverx.mediator.repository.destination.impl;

import static java.util.Arrays.asList;

import static com.leverx.mediator.repository.destination.creator.DestinationCreator.HTTP_CLIENT;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leverx.mediator.dto.request.DogRequest;
import com.leverx.mediator.dto.response.DogResponse;
import com.leverx.mediator.exception.RepositoryException;
import com.leverx.mediator.repository.DogRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/** @author Andrei Yahorau */
@Repository
@Primary
@RequiredArgsConstructor
@Slf4j
public class DogDestinationRepositoryImpl implements DogRepository {

  private final ObjectMapper mapper;

  @Value("${sap.link.dogs}")
  private final String dogsLink;

  @Override
  public DogResponse save(final DogRequest dog) {
    log.info("DogRepository. Save dog: {}", dog);

    DogResponse dogResponse;
    HttpPost post = new HttpPost(dogsLink);

    try {
      post.setEntity(new StringEntity(mapper.writeValueAsString(dog)));
      post.setHeader("Accept", "application/json");
      post.setHeader("Content-type", "application/json");
      HttpResponse httpResponse = HTTP_CLIENT.execute(post);
      String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
      dogResponse = mapper.readValue(responseString, DogResponse.class);
    } catch (UnsupportedEncodingException e) {
      log.error("DogRepository. Unsupported encoding format while convert DogResponse");
      throw new RepositoryException("Unsupported encoding format while convert DogResponse");
    } catch (JsonProcessingException | ClientProtocolException e) {
      log.error("DogRepository. Can't convert to DogResponse");
      throw new RepositoryException("Can't convert to DogResponse");
    } catch (IOException e) {
      log.error("DogRepository. Can't execute HttpPost");
      throw new RepositoryException("Can't execute HttpPost");
    }

    return dogResponse;
  }

  @Override
  public List<DogResponse> getAll() {
    log.info("DogRepository. Get all");

    HttpGet httpGet = new HttpGet(dogsLink);
    HttpResponse httpResponse;
    List<DogResponse> dogResponseList;
    try {
      httpResponse = HTTP_CLIENT.execute(httpGet);
      String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
      dogResponseList = asList(mapper.readValue(responseString, DogResponse[].class));
    } catch (JsonProcessingException | ClientProtocolException e) {
      log.error("DogRepository. Can't convert to DogResponse");
      throw new RepositoryException("Can't convert to DogResponse");
    } catch (IOException e) {
      log.error("DogRepository. Can't execute HttpPost");
      throw new RepositoryException("Can't execute HttpPost");
    }

    return dogResponseList;
  }

  @Override
  public void deleteById(final long id) {
    log.info("DogRepository. Delete dog by id: {}", id);

    HttpUriRequest httpDelete = new HttpDelete(dogsLink + "/" + id);
    try {
      HTTP_CLIENT.execute(httpDelete);
    } catch (IOException e) {
      log.error("DogRepository. Can't execute HttpDelete");
      throw new RepositoryException("Can't execute HttpDelete");
    }
  }
}
