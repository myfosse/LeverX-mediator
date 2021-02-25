package com.leverx.mediator.dto.response;

import java.time.LocalDate;

import com.leverx.mediator.dto.response.simple.SimpleUserResponseDto;
import com.leverx.mediator.model.EPetType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author Andrei Yahorau */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DogResponseDto extends PetResponseDto {

  private boolean isGuideDog;

  @Builder(builderMethodName = "dogResponseBuilder")
  public DogResponseDto(
      final long id,
      final EPetType petType,
      final String name,
      final LocalDate birthdate,
      final SimpleUserResponseDto owner,
      final boolean isGuideDog) {
    super(id, petType, name, birthdate, owner);
    this.isGuideDog = isGuideDog;
  }
}
