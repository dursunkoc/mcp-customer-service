package io.github.dursunkoc.mcpcustomerservice.domain;

import lombok.Builder;

import java.security.cert.CertPathBuilder;
import java.time.LocalDate;

@Builder
public record CustomerWrite(String identityKey, String firstName, String lastName,
                            LocalDate birthDate, String gender, String occupation, String gsmNo, String email) {
}
