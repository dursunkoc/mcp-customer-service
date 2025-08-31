package io.github.dursunkoc.mcpcustomerservice;

import io.modelcontextprotocol.server.McpServerFeatures;
import io.modelcontextprotocol.server.McpServerFeatures.*;
import io.modelcontextprotocol.spec.McpSchema;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class McpConfiguration {

    @Bean
    public ToolCallbackProvider toolCallbackProvider(CustomerServiceClient customerServiceClient) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(customerServiceClient)
                .build();
    }

    @Bean
    public List<SyncResourceSpecification> customerResources(CustomerServiceClient customerServiceClient) {
        List<SyncResourceSpecification> specifications = new ArrayList<>();

        McpSchema.Resource mcpResource = new McpSchema.Resource(
                "mcp://customers/:identity",
                "search-customer",
                "gets a customer's detailed info by identity",
                MediaType.APPLICATION_JSON_VALUE,
                null
        );
        var resourceSpecification = new SyncResourceSpecification(mcpResource, (exchange, request) -> {

            return new McpSchema.ReadResourceResult(List.of(
                    new McpSchema.TextResourceContents(
                            request.uri(),
                            MediaType.TEXT_PLAIN_VALUE,
                            "placeholder customers data"
                    )));
        });
        specifications.add(resourceSpecification);

        return specifications;
    }

    @Bean
    public List<McpServerFeatures.SyncPromptSpecification> myPrompts() {
        var prompt = new McpSchema.Prompt("create customer", "A prompt to create a new customer",
                List.of(new McpSchema.PromptArgument("identity", "Identity of the customer", true),
                        new McpSchema.PromptArgument("name", "Given name of the customer", true),
                        new McpSchema.PromptArgument("surname", "Surname of the customer", true),
                        new McpSchema.PromptArgument("dateOfBirth", "Birthdate of customer", true),
                        new McpSchema.PromptArgument("occupation", "Occupation of the customer", true),
                        new McpSchema.PromptArgument("gsmNo", "Gsm Number of the customer", true),
                        new McpSchema.PromptArgument("email", "Email of the customer", true)));

        var promptSpecification = new McpServerFeatures.SyncPromptSpecification(prompt, (exchange, getPromptRequest) -> {
            String identity = (String) getPromptRequest.arguments().get("identity");
            String name = (String) getPromptRequest.arguments().get("name");
            String surname = (String) getPromptRequest.arguments().get("surname");
            String dateOfBirth = (String) getPromptRequest.arguments().get("dateOfBirth");
            String occupation = (String) getPromptRequest.arguments().get("occupation");
            String gsmNo = (String) getPromptRequest.arguments().get("gsmNo");
            String email = (String) getPromptRequest.arguments().get("email");
            String promptTemplate = """
                       Müşteri kimlik anahtarı %s olan, adı %s soyadı %s doğum tarihi %s mesleği %s telefon numarası %s email'i ise %s olmalıdır.
                    """.formatted(identity, name, surname, dateOfBirth, occupation, gsmNo, email);

            var userMessage = new McpSchema.PromptMessage(McpSchema.Role.USER, new McpSchema.TextContent(promptTemplate));

            return new McpSchema.GetPromptResult("A new customer creation prompt.", List.of(userMessage));
        });

        return List.of(promptSpecification);
    }


}
