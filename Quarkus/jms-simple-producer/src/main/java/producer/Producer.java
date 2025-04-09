package producer;

import org.apache.camel.builder.RouteBuilder;

public class Producer extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:testqueueProducer?period=5000")
                .log("Sending message to testqueue: ${body}")
                .to("jms:queue:testqueue");

    }
}
