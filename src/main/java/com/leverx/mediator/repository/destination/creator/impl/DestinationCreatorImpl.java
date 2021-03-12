package com.leverx.mediator.repository.destination.creator.impl;

import static com.leverx.mediator.repository.destination.constant.DestinationConstants.HEADER_ACCEPT;
import static com.leverx.mediator.repository.destination.constant.DestinationConstants.HEADER_CONTENT_TYPE;
import static com.leverx.mediator.repository.destination.constant.DestinationConstants.HTTP_CLIENT;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leverx.mediator.repository.destination.creator.DestinationCreator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/** @author Andrei Yahorau */
@Component
@RequiredArgsConstructor
@Slf4j
public class DestinationCreatorImpl implements DestinationCreator {

  @Value("${sap.request.accept-type}")
  private final String acceptType;

  @Value("${sap.request.content-type}")
  private final String contentType;

  private final ObjectMapper mapper;

  @Override
  public HttpResponse executeHttpGet(final String url) throws IOException {
    log.info("HttpResponse. Get request by URL: {}", url);

    HttpGet get = new HttpGet(url);

    return HTTP_CLIENT.execute(get);
  }

  @Override
  public HttpResponse executeHttpPost(final String url, final Object entity) throws IOException {
    log.info("HttpResponse. Post request by URL {} with entity {}", url, entity);

    HttpPost post = new HttpPost(url);
    post.setEntity(new StringEntity(mapper.writeValueAsString(entity)));
    post.setHeader(HEADER_ACCEPT, acceptType);
    post.setHeader(HEADER_CONTENT_TYPE, contentType);

    return HTTP_CLIENT.execute(post);
  }

  @Override
  public HttpResponse executeHttpDelete(final String url) throws IOException {
    log.info("HttpResponse. Delete request by URL: {}", url);

    HttpDelete delete = new HttpDelete(url);

    return HTTP_CLIENT.execute(delete);
  }
}
