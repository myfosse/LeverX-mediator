package com.leverx.mediator.repository.destination.creator;

import java.io.IOException;

import org.apache.http.HttpResponse;

/** @author Andrei Yahorau */
public interface DestinationCreator {

  HttpResponse executeHttpGet(final String url) throws IOException;

  HttpResponse executeHttpPost(final String url, final Object entity) throws IOException;

  HttpResponse executeHttpDelete(final String url) throws IOException;
}
