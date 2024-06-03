
package it.producer.scheduledservice;

import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchedProducerService {

    @Autowired
    private ProducerTemplate producerTemplate;

    private static final Logger log = LoggerFactory.getLogger(SchedProducerService.class);

    public void buildBody() throws Exception {
        log.info("test producer service");
        String body = "test message";
        producerTemplate.sendBody("direct:message-producer", body);
    }
}
