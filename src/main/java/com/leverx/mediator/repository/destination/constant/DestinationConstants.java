package com.leverx.mediator.repository.destination.constant;

import static com.sap.cloud.sdk.cloudplatform.connectivity.DestinationAccessor.getDestination;
import static com.sap.cloud.sdk.cloudplatform.connectivity.HttpClientAccessor.getHttpClient;

import static lombok.AccessLevel.PRIVATE;

import org.apache.http.client.HttpClient;

import com.sap.cloud.sdk.cloudplatform.connectivity.HttpDestination;

import lombok.NoArgsConstructor;

/** @author Andrei Yahorau */
@NoArgsConstructor(access = PRIVATE)
public final class DestinationConstants {

  public static final String DESTINATION_NAME = "AdminAuth";

  public static final HttpDestination DESTINATION = getDestination(DESTINATION_NAME).asHttp();

  public static final HttpClient HTTP_CLIENT = getHttpClient(DESTINATION);

  public static final String PREFIX = DESTINATION.getUri() + "/api/v1";

  public static final String PATH_CATS = PREFIX + "/cats";

  public static final String PATH_DOGS = PREFIX + "/dogs";

  public static final String PATH_USERS = PREFIX + "/users";
}
