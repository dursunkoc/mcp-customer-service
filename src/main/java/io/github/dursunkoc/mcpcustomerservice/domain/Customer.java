package io.github.dursunkoc.mcpcustomerservice.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private Long id;
    private String identityKey;
    //private List<CustomerContact> customerContacts = new ArrayList<>();
    private CustomerDemographics customerDemographics;
}
