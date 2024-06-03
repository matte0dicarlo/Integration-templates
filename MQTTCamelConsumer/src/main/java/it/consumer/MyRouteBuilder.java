package it.consumer;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("paho-mqtt5:{{topicname}}?brokerUrl=tcp://{{brokeraddress}}:{{brokerport}}")
                .log("Received message: ${body}");
    }
}
