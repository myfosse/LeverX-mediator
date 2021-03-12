package com.leverx.mediator.repository.destination.impl;

import static com.leverx.mediator.dto.converter.UserConverter.convertStringToListOfUserResponse;
import static com.leverx.mediator.dto.converter.UserConverter.convertStringToUserResponse;
import static com.leverx.mediator.repository.destination.constant.DestinationConstants.CHARSET_UTF_8;
import static com.leverx.mediator.repository.destination.constant.DestinationConstants.ENDPOINT_USERS;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.util.EntityUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.leverx.mediator.dto.request.UserRequest;
import com.leverx.mediator.dto.response.UserResponse;
import com.leverx.mediator.exception.RepositoryException;
import com.leverx.mediator.repository.UserRepository;
import com.leverx.mediator.repository.destination.creator.DestinationCreator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/** @author Andrei Yahorau */
@Repository
@Primary
@RequiredArgsConstructor
@Slf4j
public class UserDestinationRepositoryImpl implements UserRepository {

  private final DestinationCreator destinationCreator;

  @Override
  public UserResponse save(final UserRequest user) {
    log.info("UserRepository. Save user: {}", user);

    UserResponse userResponse;

    try {

      HttpResponse httpResponse = destinationCreator.executeHttpPost(ENDPOINT_USERS, user);
      String responseString = EntityUtils.toString(httpResponse.getEntity(), CHARSET_UTF_8);
      userResponse = convertStringToUserResponse(responseString);

    } catch (final UnsupportedEncodingException e) {
      log.error("UserRepository. Unsupported encoding format while convert UserResponse");
      throw new RepositoryException("Unsupported encoding format while convert UserResponse");
    } catch (final JsonProcessingException | ClientProtocolException e) {
      log.error("UserRepository. Can't convert to DogResponse");
      throw new RepositoryException("Can't convert to DogResponse");
    } catch (final IOException e) {
      log.error("UserRepository. Can't execute HttpPost");
      throw new RepositoryException("Can't execute HttpPost");
    }

    return userResponse;
  }

  @Override
  public List<UserResponse> getAll() {
    log.info("UserRepository. Get all");

    List<UserResponse> userResponseList;

    try {

      HttpResponse httpResponse = destinationCreator.executeHttpGet(ENDPOINT_USERS);
      String responseString = EntityUtils.toString(httpResponse.getEntity(), CHARSET_UTF_8);
      userResponseList = convertStringToListOfUserResponse(responseString);

    } catch (final JsonProcessingException | ClientProtocolException e) {
      log.error("UserRepository. Can't convert to UserResponse");
      throw new RepositoryException("Can't convert to UserResponse");
    } catch (final IOException e) {
      log.error("UserRepository. Can't execute HttpPost");
      throw new RepositoryException("Can't execute HttpPost");
    }

    return userResponseList;
  }

  @Override
  public void deleteById(final long id) {
    log.info("UserRepository. Delete user by id: {}", id);

    String url = ENDPOINT_USERS + "/" + id;

    try {
      destinationCreator.executeHttpDelete(url);
    } catch (final IOException e) {
      log.error("UserRepository. Can't execute HttpDelete");
      throw new RepositoryException("Can't execute HttpDelete");
    }
  }
}
