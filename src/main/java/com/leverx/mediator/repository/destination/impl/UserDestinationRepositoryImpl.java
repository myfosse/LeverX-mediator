package com.leverx.mediator.repository.destination.impl;

import static java.nio.charset.StandardCharsets.UTF_8;

import static com.leverx.mediator.converter.ObjectConverter.convertJsonStringToListOfObjects;
import static com.leverx.mediator.converter.ObjectConverter.convertJsonStringToObject;
import static com.leverx.mediator.converter.ObjectConverter.convertObjectToJsonString;
import static com.leverx.mediator.repository.destination.constant.DestinationConstants.PATH_USERS;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.leverx.mediator.dto.request.UserRequest;
import com.leverx.mediator.dto.response.UserResponse;
import com.leverx.mediator.exception.RepositoryException;
import com.leverx.mediator.repository.UserRepository;
import com.leverx.mediator.repository.destination.creator.DestinationCreator;

import lombok.extern.slf4j.Slf4j;

/** @author Andrei Yahorau */
@Slf4j
@Profile("destination")
@Repository
public class UserDestinationRepositoryImpl implements UserRepository {

  private final DestinationCreator destinationCreator;

  @Autowired
  public UserDestinationRepositoryImpl(final DestinationCreator destinationCreator) {
    this.destinationCreator = destinationCreator;
  }

  @Override
  public UserResponse save(final UserRequest user) {
    log.info("UserRepository. Save user: {}", user);

    try {

      HttpResponse httpResponse =
          destinationCreator.executeHttpPost(PATH_USERS, convertObjectToJsonString(user));
      String responseString = EntityUtils.toString(httpResponse.getEntity(), UTF_8);
      return convertJsonStringToObject(responseString, UserResponse.class);

    } catch (final UnsupportedEncodingException e) {
      log.error("UserRepository. Unsupported encoding format while convert UserResponse");
      throw new RepositoryException("Unsupported encoding format while convert UserResponse");

    } catch (final JsonProcessingException e) {
      log.error("UserRepository. Can't convert to DogResponse");
      throw new RepositoryException("Can't convert to DogResponse");

    } catch (final IOException e) {
      log.error("UserRepository. Can't execute HttpPost");
      throw new RepositoryException("Can't execute HttpPost");
    }
  }

  @Override
  public List<UserResponse> getAll() {
    log.info("UserRepository. Get all");

    try {

      HttpResponse httpResponse = destinationCreator.executeHttpGet(PATH_USERS);
      String responseString = EntityUtils.toString(httpResponse.getEntity(), UTF_8);
      return convertJsonStringToListOfObjects(responseString, UserResponse[].class);

    } catch (final JsonProcessingException e) {
      log.error("UserRepository. Can't convert to UserResponse");
      throw new RepositoryException("Can't convert to UserResponse");

    } catch (final IOException e) {
      log.error("UserRepository. Can't execute HttpPost");
      throw new RepositoryException("Can't execute HttpPost");
    }
  }

  @Override
  public void deleteById(final long id) {
    log.info("UserRepository. Delete user by id: {}", id);

    String url = PATH_USERS + "/" + id;

    try {

      destinationCreator.executeHttpDelete(url);

    } catch (final IOException e) {
      log.error("UserRepository. Can't execute HttpDelete");
      throw new RepositoryException("Can't execute HttpDelete");
    }
  }
}
