package com.leverx.mediator.transactional;

/** @author Andrei Yahorau */
public interface Transaction<T, R> {

  T executeSave(final R entity);

  void rollback();
}
