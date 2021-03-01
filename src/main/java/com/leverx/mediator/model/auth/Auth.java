package com.leverx.mediator.model.auth;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import lombok.Data;

/** @author Andrei Yahorau */
@Component
@RequestScope
@Data
public class Auth {

  private String auth;
}
