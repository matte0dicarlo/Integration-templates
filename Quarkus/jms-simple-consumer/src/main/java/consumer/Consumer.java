package consumer;

import org.apache.camel.builder.RouteBuilder;

public class Consumer extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("jms:queue:testqueue")
                .log("Body: ${body}");
    }
}