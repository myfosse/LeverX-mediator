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

  public static final String HEADER_ACCEPT = "Accept";

  public static final String HEADER_CONTENT_TYPE = "Content-type";

  public static final String CHARSET_UTF_8 = "UTF-8";

  public static final String PREFIX = DESTINATION.getUri() + "/api/v1";

  public static final String ENDPOINT_CATS  = PREFIX + "/cats";

  public static final String ENDPOINT_DOGS  = PREFIX + "/dogs";

  public static final String ENDPOINT_USERS = PREFIX + "/users";
}
