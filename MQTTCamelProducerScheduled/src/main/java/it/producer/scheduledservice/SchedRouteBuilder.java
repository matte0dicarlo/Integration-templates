package it.producer.scheduledservice;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SchedRouteBuilder extends RouteBuilder {

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
                .to("paho-mqtt5:{{topicname}}?brokerUrl=tcp://{{brokeraddress}}:{{brokerport}}");
    }

}

