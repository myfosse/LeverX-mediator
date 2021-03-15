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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.leverx.mediator.repository.destination.creator.DestinationCreator;

import lombok.extern.slf4j.Slf4j;

/** @author Andrei Yahorau */
@Slf4j
@Profile("destination")
@Component
public class DestinationCreatorImpl implements DestinationCreator {

  private final String acceptType;

  private final String contentType;

  @Autowired
  public DestinationCreatorImpl(
      @Value("${sap.request.accept-type}") final String acceptType,
      @Value("${sap.request.content-type}") final String contentType) {
    this.acceptType = acceptType;
    this.contentType = contentType;
  }

  @Override
  public HttpResponse executeHttpGet(final String url) throws IOException {
    log.info("HttpResponse. Get request by URL: {}", url);

    HttpGet get = new HttpGet(url);

    return HTTP_CLIENT.execute(get);
  }

  @Override
  public HttpResponse executeHttpPost(final String url, final String jsonObject)
      throws IOException {
    log.info("HttpResponse. Post request by URL {} with entity {}", url, jsonObject);

    HttpPost post = new HttpPost(url);
    post.setEntity(new StringEntity(jsonObject));
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
