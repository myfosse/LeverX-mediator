package com.leverx.mediator.transactional.impl;

import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;
import static org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes;

import static com.leverx.mediator.transactional.constant.AttributeConstants.CAT_ID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import com.leverx.mediator.dto.request.CatRequest;
import com.leverx.mediator.dto.response.CatResponse;
import com.leverx.mediator.repository.CatRepository;
import com.leverx.mediator.transactional.CatTransaction;

import lombok.extern.slf4j.Slf4j;

/** @author Andrei Yahorau */
@Component
@Slf4j
public class CatTransactionImpl implements CatTransaction {

  private final CatRepository catRepository;

  @Autowired
  public CatTransactionImpl(final CatRepository catRepository) {
    this.catRepository = catRepository;
  }

  @Override
  public CatResponse executeSave(final CatRequest cat) {
    log.info("CatTransaction. Save cat: {}", cat);

    try {
      CatResponse catResponse = catRepository.save(cat);
      currentRequestAttributes().setAttribute(CAT_ID, catResponse.getId(), SCOPE_REQUEST);
      return catResponse;
    } catch (final HttpClientErrorException exception) {
      log.error("CatTransaction. Can't save cat: {}", cat);
      throw exception;
    }
  }

  @Override
  public void rollback() {

    long id = (long) currentRequestAttributes().getAttribute(CAT_ID, SCOPE_REQUEST);

    log.info("CatTransaction. Rollback cat with id: {}", id);

    catRepository.deleteById(id);
  }
}
