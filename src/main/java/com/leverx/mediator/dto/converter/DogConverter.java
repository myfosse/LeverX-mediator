package com.leverx.mediator.dto.converter;

import static java.util.Arrays.asList;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leverx.mediator.dto.response.DogResponse;

import lombok.NoArgsConstructor;

/** @author Andrei Yahorau */
@NoArgsConstructor(access = PRIVATE)
public final class DogConverter {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  public static DogResponse convertStringToDogResponse(final String dog)
      throws JsonProcessingException {
    return MAPPER.readValue(dog, DogResponse.class);
  }

  public static List<DogResponse> convertStringToListOfDogResponse(final String dogs)
      throws JsonProcessingException {
    return asList(MAPPER.readValue(dogs, DogResponse[].class));
  }
}
