package com.leverx.mediator.dto.converter;

import static java.util.Arrays.asList;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leverx.mediator.dto.request.CatRequest;
import com.leverx.mediator.dto.response.CatResponse;

import lombok.NoArgsConstructor;

/** @author Andrei Yahorau */
@NoArgsConstructor(access = PRIVATE)
public final class CatConverter {

  private static final ObjectMapper mapper = new ObjectMapper();

  public static String convertCatToJsonString(final CatRequest cat) throws JsonProcessingException {
    return mapper.writeValueAsString(cat);
  }

  public static CatResponse convertStringToCatResponse(final String cat)
      throws JsonProcessingException {
    return mapper.readValue(cat, CatResponse.class);
  }

  public static List<CatResponse> convertStringToListOfCatResponse(final String cats)
      throws JsonProcessingException {
    return asList(mapper.readValue(cats, CatResponse[].class));
  }
}
