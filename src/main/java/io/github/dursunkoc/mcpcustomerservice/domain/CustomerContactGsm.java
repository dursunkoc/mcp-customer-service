package io.github.dursunkoc.mcpcustomerservice.domain;

import io.github.dursunkoc.mcpcustomerservice.enums.CustomerContactType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public final class CustomerContactGsm extends CustomerContact{
    private final String gsmNo;

    public CustomerContactGsm(String gsmNo) {
        super(CustomerContactType.GSM);
        this.gsmNo = gsmNo;
    }
}
