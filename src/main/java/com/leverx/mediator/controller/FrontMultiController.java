package com.leverx.mediator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.leverx.mediator.dto.response.every.EveryListResponse;
import com.leverx.mediator.model.auth.Auth;
import com.leverx.mediator.service.MultiService;

import lombok.extern.slf4j.Slf4j;

/** @author Andrei Yahorau */
@RestController
@RequestMapping("/api/v1")
@Slf4j
public class FrontMultiController {

  private final MultiService multiService;
  private final Auth auth;

  @Autowired
  public FrontMultiController(final MultiService multiService, final Auth auth) {
    this.multiService = multiService;
    this.auth = auth;
  }

  @GetMapping("/all")
  @RequestScope
  public ResponseEntity<EveryListResponse> getAllLists(
      @RequestHeader("Authorization") String authHeader) {
    log.info("Get all lists request: {}", authHeader);

    auth.setAuth(authHeader);
    return ResponseEntity.ok(multiService.getAllLists());
  }
}
