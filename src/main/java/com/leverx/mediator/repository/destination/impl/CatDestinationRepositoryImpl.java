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
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leverx.mediator.dto.request.CatRequest;
import com.leverx.mediator.dto.response.CatResponse;
import com.leverx.mediator.exception.RepositoryException;
import com.leverx.mediator.repository.CatRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/** @author Andrei Yahorau */
@Repository
@Primary
@RequiredArgsConstructor
@Slf4j
public class CatDestinationRepositoryImpl implements CatRepository {

  private final ObjectMapper mapper;

  @Value("${sap.link.cats}")
  private final String catsLink;

  @Override
  public CatResponse save(final CatRequest cat) {
    log.info("CatRepository. Save cat: {}", cat);

    CatResponse catResponse;

    HttpPost post = new HttpPost(catsLink);
    try {
      post.setEntity(new StringEntity(mapper.writeValueAsString(cat)));
      post.setHeader("Accept", "application/json");
      post.setHeader("Content-type", "application/json");
      HttpResponse httpResponse = HTTP_CLIENT.execute(post);
      String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
      catResponse = mapper.readValue(responseString, new TypeReference<>() {});

    } catch (UnsupportedEncodingException e) {
      log.error("CatRepository. Unsupported encoding format while convert CatResponse");
      throw new RepositoryException("Unsupported encoding format while convert CatResponse");
    } catch (JsonProcessingException | ClientProtocolException e) {
      log.error("CatRepository. Can't convert to CatResponse");
      throw new RepositoryException("Can't convert to CatResponse");
    } catch (IOException e) {
      log.error("CatRepository. Can't execute HttpPost");
      throw new RepositoryException("Can't execute HttpPost");
    }

    return catResponse;
  }

  @Override
  public List<CatResponse> getAll() {
    log.info("CatRepository. Get all");

    HttpGet httpGet = new HttpGet(catsLink);
    HttpResponse httpResponse;
    List<CatResponse> dogResponseList;
    try {
      httpResponse = HTTP_CLIENT.execute(httpGet);
      String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
      dogResponseList = asList(mapper.readValue(responseString, CatResponse[].class));
    } catch (JsonProcessingException | ClientProtocolException e) {
      log.error("CatRepository. Can't convert to CatResponse");
      throw new RepositoryException("Can't convert to CatResponse");
    } catch (IOException e) {
      log.error("CatRepository. Can't execute HttpPost");
      throw new RepositoryException("Can't execute HttpPost");
    }

    return dogResponseList;
  }

  @Override
  public void deleteById(final long id) {
    log.info("CatRepository. Delete cat by id: {}", id);

    HttpDelete httpDelete = new HttpDelete(catsLink + "/" + id);
    try {
      HTTP_CLIENT.execute(httpDelete);
    } catch (IOException e) {
      log.error("CatRepository. Can't execute HttpDelete");
      throw new RepositoryException("Can't execute HttpDelete");
    }
  }
}
