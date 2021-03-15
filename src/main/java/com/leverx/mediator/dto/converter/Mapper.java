package com.leverx.mediator.dto.converter;

import static lombok.AccessLevel.PRIVATE;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.NoArgsConstructor;

/**
 * @author Andrei Yahorau
 */
@NoArgsConstructor(access = PRIVATE)
public final class Mapper {

  public static final ObjectMapper MAPPER = new ObjectMapper();
}
