package it.producer;

import it.producer.ProducerService.ProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Exception {
        ApplicationContext context = SpringApplication.run(Application.class, args);
        log.info("-- MAIN --");

        ProducerService producerService = context.getBean(ProducerService.class);
        producerService.buildBody();
    }
}