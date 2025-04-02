package io.github.dursunkoc.mcpcustomerservice;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class McpConfiguration {

    @Bean
    public ToolCallbackProvider toolCallbackProvider(CustomerServiceClient customerServiceClient) {
        return MethodToolCallbackProvider.builder().toolObjects(customerServiceClient).build();
    }
}
