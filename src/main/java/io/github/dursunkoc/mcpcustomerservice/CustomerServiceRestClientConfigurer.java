package io.github.dursunkoc.mcpcustomerservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestClient;

@Configuration
public class CustomerServiceRestClientConfigurer {

    @Bean(name = "customerServiceRestClient")
    @Scope("prototype")
    public RestClient restClient(){
        return RestClient.builder()
                .baseUrl("http://localhost:8080")
                .build();
    }

}
