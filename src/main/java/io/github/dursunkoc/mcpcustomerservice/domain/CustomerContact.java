package io.github.dursunkoc.mcpcustomerservice.domain;


import io.github.dursunkoc.mcpcustomerservice.enums.CustomerContactType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract sealed class CustomerContact permits CustomerContactGsm, CustomerContactEmail {
    private final CustomerContactType type;
    public CustomerContact of(CustomerContactType type, String attribute){
        return switch (type) {
            case GSM -> new CustomerContactGsm(attribute);
            case EMAIL -> new CustomerContactEmail(attribute);
        };
    }
}
