package io.github.dursunkoc.mcpcustomerservice;

import io.github.dursunkoc.mcpcustomerservice.domain.Customer;
import io.github.dursunkoc.mcpcustomerservice.domain.CustomerWrite;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class CustomerServiceClient {

    private final RestClient restClient;
    public CustomerServiceClient(@Qualifier("customerServiceRestClient") RestClient restClient) {
        this.restClient = restClient;
    }

    @Tool(name = "createCustomer", description = "Creates a new customer with given parameters")
    public Customer createCustomer(CustomerWrite customerWrite){
        return restClient.post()
                .uri(uriBuilder -> uriBuilder.path("/new").build())
                .body(customerWrite)
                .header("Content-Type", "application/json")
                .retrieve()
                .body(Customer.class);
    }

    @Tool(name = "getAllCustomers", description = "Returns whole customer list")
    public List<Customer> getAllCustomers(){
        return restClient.get()
                .uri(uriBuilder -> uriBuilder.path("/all").build())
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }
}
