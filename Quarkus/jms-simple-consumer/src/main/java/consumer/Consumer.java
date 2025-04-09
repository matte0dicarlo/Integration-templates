package consumer;

import jakarta.enterprise.inject.Produces;
import jakarta.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.builder.RouteBuilder;

public class Consumer extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("jms:queue:test")
                .log("Body: ${body}");
    }


    @Produces
    public ConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory("tcp://123.123.123.1:61816");
    }



}