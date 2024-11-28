package it.producer.scheduledservice;

import jakarta.jms.ConnectionFactory;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.stereotype.Component;

@Component
public class SchedRouteBuilder extends RouteBuilder {

    private static final Logger log = LoggerFactory.getLogger(SchedRouteBuilder.class);

    @Override
    public void configure() throws Exception {

        onException(Exception.class)
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        Exception exception = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
                        exception.printStackTrace();
                    }
                })
                .handled(true);
        
        from("direct:message-producer")
                .routeId("jmsProducerRoute")
                .transacted()
                .process(exchange -> {
                    String message = "Hello from Camel at " + System.currentTimeMillis();
                    exchange.getIn().setBody(message);
                    log.info("Generated message: {}", message);
                })
                .to("jms:queue:simplequeue")
                .log("Message successfully sent to AMQ: ${body}");
    }
    @Bean
    public JmsTransactionManager jmsTransactionManager(ConnectionFactory connectionFactory) {
        return new JmsTransactionManager(connectionFactory);
    }
}


