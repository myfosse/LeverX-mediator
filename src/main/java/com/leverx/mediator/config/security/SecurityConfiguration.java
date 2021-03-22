package com.leverx.mediator.config.security;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import static com.leverx.mediator.constant.EndpointConstant.ENDPOINT_ALL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.Jwt;

import com.sap.cloud.security.xsuaa.XsuaaServiceConfiguration;
import com.sap.cloud.security.xsuaa.extractor.IasXsuaaExchangeBroker;
import com.sap.cloud.security.xsuaa.token.TokenAuthenticationConverter;

@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final XsuaaServiceConfiguration xsuaaServiceConfiguration;

  @Autowired
  public SecurityConfiguration(final XsuaaServiceConfiguration xsuaaServiceConfiguration) {
    this.xsuaaServiceConfiguration = xsuaaServiceConfiguration;
  }

  @Override
  protected void configure(final HttpSecurity http) throws Exception {

    http
        .sessionManagement().sessionCreationPolicy(STATELESS)
        .and()
          .authorizeRequests()
          .antMatchers("/*").authenticated()
          .antMatchers(GET, ENDPOINT_ALL).hasAuthority("Read")
          .antMatchers(POST, ENDPOINT_ALL).hasAuthority("Update")
          .anyRequest().denyAll()
        .and()
          .oauth2ResourceServer()
          .bearerTokenResolver(new IasXsuaaExchangeBroker(xsuaaServiceConfiguration))
          .jwt()
          .jwtAuthenticationConverter(xsuaaTokenConverter());
  }

  private Converter<Jwt, AbstractAuthenticationToken> xsuaaTokenConverter() {
    return new TokenAuthenticationConverter(xsuaaServiceConfiguration)
        .setLocalScopeAsAuthorities(true);
  }
}
