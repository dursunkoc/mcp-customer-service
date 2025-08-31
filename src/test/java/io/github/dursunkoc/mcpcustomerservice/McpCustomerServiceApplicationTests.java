package io.github.dursunkoc.mcpcustomerservice;

import io.github.dursunkoc.mcpcustomerservice.domain.Customer;
import io.github.dursunkoc.mcpcustomerservice.domain.CustomerWrite;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class McpCustomerServiceApplicationTests {

    @Autowired
    private CustomerServiceClient customerServiceClient;

    @Test
    void contextLoads() {
        Customer initialCustomer = customerServiceClient.createCustomer(CustomerWrite.builder()
                .identityKey("12312312")
                .firstName("Abuzer")
                .lastName("Yaprakezer")
                .email("abzer@gmail.com")
                .gsmNo("5301234567")
                .birthDate(LocalDate.of(1990, 1, 1))
                .gender("E")
                .occupation("Engineer")
                .build());
        System.out.println("Initial customer: "+initialCustomer+"...");
        List<Customer> allCustomers = customerServiceClient.getAllCustomers();
        System.out.println(allCustomers);
    }

}
