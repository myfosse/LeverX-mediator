package com.leverx.mediator.controller;

import static com.leverx.mediator.constant.EndpointConstant.ENDPOINT_ALL;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leverx.mediator.dto.request.multi.UserCatDogRequest;
import com.leverx.mediator.dto.response.multi.UserCatDogListResponse;
import com.leverx.mediator.dto.response.multi.UserCatDogResponse;
import com.leverx.mediator.model.auth.Auth;
import com.leverx.mediator.service.MultiService;

import lombok.extern.slf4j.Slf4j;

/** @author Andrei Yahorau */
@RestController
@RequestMapping(ENDPOINT_ALL)
@Slf4j
public class MultiController {

  private final MultiService multiService;
  private final Auth auth;

  @Autowired
  public MultiController(final MultiService multiService, final Auth auth) {
    this.multiService = multiService;
    this.auth = auth;
  }

  @GetMapping
  public ResponseEntity<UserCatDogListResponse> getAllLists(
      @RequestHeader(value = "Authorization", defaultValue = "") final String authHeader) {
    log.info("Controller. Get lists of all entities");

    auth.setAuth(authHeader);

    return ResponseEntity.ok(multiService.getAllUsersAndPets());
  }

  @PostMapping
  public ResponseEntity<UserCatDogResponse> saveUserWithPets(
      @RequestHeader(value = "Authorization", defaultValue = "") final String authHeader,
      @Valid @RequestBody final UserCatDogRequest userCatDogRequest) {
    log.info("Controller. Save user, cat, dog request: {}", userCatDogRequest);

    auth.setAuth(authHeader);

    return ResponseEntity.ok(multiService.save(userCatDogRequest));
  }
}
