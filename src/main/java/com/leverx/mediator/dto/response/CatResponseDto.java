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
public class CatResponseDto extends PetResponseDto {

  private boolean isBold;

  @Builder(builderMethodName = "catResponseBuilder")
  public CatResponseDto(
      final long id,
      final EPetType petType,
      final String name,
      final LocalDate birthdate,
      final SimpleUserResponseDto owner,
      final boolean isBold) {
    super(id, petType, name, birthdate, owner);
    this.isBold = isBold;
  }
}
