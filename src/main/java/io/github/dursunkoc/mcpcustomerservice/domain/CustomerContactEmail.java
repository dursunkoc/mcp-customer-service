package io.github.dursunkoc.mcpcustomerservice.domain;


import io.github.dursunkoc.mcpcustomerservice.enums.CustomerContactType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public final class CustomerContactEmail extends CustomerContact {
    private final String email;

    public CustomerContactEmail(String email) {
        super(CustomerContactType.EMAIL);
        this.email = email;
    }
}
