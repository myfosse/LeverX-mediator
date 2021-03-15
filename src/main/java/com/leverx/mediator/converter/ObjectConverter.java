package com.leverx.mediator.converter;

import static java.util.Arrays.asList;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.NoArgsConstructor;

/** @author Andrei Yahorau */
@NoArgsConstructor(access = PRIVATE)
public final class ObjectConverter {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  public static String convertObjectToJsonString(final Object object)
      throws JsonProcessingException {
    return MAPPER.writeValueAsString(object);
  }

  public static <T> T convertJsonStringToObject(final String jsonObject, final Class<T> valueType)
      throws JsonProcessingException {
    return MAPPER.readValue(jsonObject, valueType);
  }

  public static <T> List<T> convertJsonStringToListOfObjects(
      final String jsonList, final Class<T[]> valueType) throws JsonProcessingException {
    return asList(MAPPER.readValue(jsonList, valueType));
  }
}
