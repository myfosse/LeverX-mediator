package com.leverx.mediator.dto.converter;

import static java.util.Arrays.asList;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leverx.mediator.dto.request.UserRequest;
import com.leverx.mediator.dto.response.UserResponse;

import lombok.NoArgsConstructor;

/** @author Andrei Yahorau */
@NoArgsConstructor(access = PRIVATE)
public final class UserConverter {

  private static final ObjectMapper mapper = new ObjectMapper();

  public static String convertUserToJsonString(final UserRequest user) throws JsonProcessingException {
    return mapper.writeValueAsString(user);
  }

  public static UserResponse convertStringToUserResponse(final String user)
      throws JsonProcessingException {
    return mapper.readValue(user, UserResponse.class);
  }

  public static List<UserResponse> convertStringToListOfUserResponse(final String users)
      throws JsonProcessingException {
    return asList(mapper.readValue(users, UserResponse[].class));
  }
}
