package com.leverx.mediator.repository.destination.creator;

import static lombok.AccessLevel.PRIVATE;

import org.apache.http.client.HttpClient;

import com.sap.cloud.sdk.cloudplatform.connectivity.DestinationAccessor;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpClientAccessor;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpDestination;

import lombok.NoArgsConstructor;

/** @author Andrei Yahorau */
@NoArgsConstructor(access = PRIVATE)
public final class DestinationCreator {

  private static final String DESTINATION_NAME = "AdminAuth";

  public static final HttpDestination DESTINATION =
      DestinationAccessor.getDestination(DESTINATION_NAME).asHttp();

  public static final HttpClient HTTP_CLIENT = HttpClientAccessor.getHttpClient(DESTINATION);
}
