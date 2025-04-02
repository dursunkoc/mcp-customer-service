package io.github.dursunkoc.mcpcustomerservice.domain;

import io.github.dursunkoc.mcpcustomerservice.enums.Gender;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDemographics {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Gender gender;
    private String occupation;
}
