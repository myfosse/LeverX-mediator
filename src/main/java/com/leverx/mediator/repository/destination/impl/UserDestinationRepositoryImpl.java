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
import com.leverx.mediator.dto.request.UserRequest;
import com.leverx.mediator.dto.response.UserResponse;
import com.leverx.mediator.exception.RepositoryException;
import com.leverx.mediator.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/** @author Andrei Yahorau */
@Repository
@Primary
@RequiredArgsConstructor
@Slf4j
public class UserDestinationRepositoryImpl implements UserRepository {

  private final ObjectMapper mapper;

  @Value("${sap.link.users}")
  private final String usersLink;

  @Override
  public UserResponse save(final UserRequest user) {
    log.info("UserRepository. Save user: {}", user);

    UserResponse userResponse;
    HttpPost post = new HttpPost(usersLink);

    try {
      post.setEntity(new StringEntity(mapper.writeValueAsString(user)));
      post.setHeader("Accept", "application/json");
      post.setHeader("Content-type", "application/json");
      HttpResponse httpResponse = HTTP_CLIENT.execute(post);
      String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
      userResponse = mapper.readValue(responseString, UserResponse.class);
    } catch (UnsupportedEncodingException e) {
      log.error("UserRepository. Unsupported encoding format while convert UserResponse");
      throw new RepositoryException("Unsupported encoding format while convert UserResponse");
    } catch (JsonProcessingException | ClientProtocolException e) {
      log.error("UserRepository. Can't convert to UserResponse");
      throw new RepositoryException("Can't convert to UserResponse");
    } catch (IOException e) {
      log.error("UserRepository. Can't execute HttpPost");
      throw new RepositoryException("Can't execute HttpPost");
    }

    return userResponse;
  }

  @Override
  public List<UserResponse> getAll() {
    log.info("UserRepository. Get all");

    HttpGet httpGet = new HttpGet(usersLink);
    HttpResponse httpResponse;
    List<UserResponse> userResponseList;
    try {
      httpResponse = HTTP_CLIENT.execute(httpGet);
      String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
      userResponseList = asList(mapper.readValue(responseString, UserResponse[].class));
    } catch (JsonProcessingException | ClientProtocolException e) {
      log.error("UserRepository. Can't convert to UserResponse");
      throw new RepositoryException("Can't convert to UserResponse");
    } catch (IOException e) {
      log.error("UserRepository. Can't execute HttpPost");
      throw new RepositoryException("Can't execute HttpPost");
    }

    return userResponseList;
  }

  @Override
  public void deleteById(final long id) {
    log.info("UserRepository. Delete user by id: {}", id);

    HttpUriRequest httpDelete = new HttpDelete(usersLink + "/" + id);
    try {
      HTTP_CLIENT.execute(httpDelete);
    } catch (IOException e) {
      log.error("UserRepository. Can't execute HttpDelete");
      throw new RepositoryException("Can't execute HttpDelete");
    }
  }
}
