package com.rabbitmq.producer;

import config.ApplicationConfig;
import entity.Employee;
import entity.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import service.EmployeeJsonProducer;
import service.HumanResourceProducer;
import service.PictureProducer;
import service.PictureProducerTopicExchange;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
@ComponentScan(basePackages = {"service", "entity", "config"})
@EnableConfigurationProperties(ApplicationConfig.class)
public class ProducerApplication implements CommandLineRunner {

	@Autowired
	private EmployeeJsonProducer employeeJsonProducer;

	@Autowired
	private HumanResourceProducer humanResourceProducer;

	@Autowired
	private PictureProducer pictureProducer;

	@Autowired
	private PictureProducerTopicExchange pictureProducerTopicExchange;

	@Autowired
	private ApplicationConfig applicationConfig;

	private final List<String> SOURCES = Arrays.asList("mobile", "web");
	private final List<String> TYPES = Arrays.asList("jpg", "png", "svg");


	public static void main(String[] args) {
		SpringApplication.run(ProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		if(applicationConfig.isEnableEmployeeJsonProducer()) {

			// Publishing in JSON format
			for(int i=1; i<=5; i++) {
				Employee employee = new Employee("name "+i, "empID "+i, LocalDate.now());
				employeeJsonProducer.sendMessage(employee);
			}
		}

		if(applicationConfig.isEnableHumanResourceProducer()) {

            // publish to exchange, FANOUT EXCHANGE.
            // routing key is not required, as FANOUT sends to all binded queues.
		    for(int i=1; i<=5; i++) {
                Employee employee = new Employee("name " + i, "empID " + i, LocalDate.now());
                humanResourceProducer.publishToExchange(employee);
            }
        }

        if(applicationConfig.isEnablePictureProducer()) {

			// publish to exchange, DIRECT EXCHANGE
			// multiple queues are binded to exchange, send messages only to matching routing key.
			for(int i=1; i<=5; i++) {
				Picture picture = new Picture();

				picture.setName("name "+i);
				picture.setSize(ThreadLocalRandom.current().nextLong(1, 10001));
				picture.setSource(SOURCES.get(i % SOURCES.size()));
				picture.setType(TYPES.get(i % TYPES.size()));


				pictureProducer.sendMessage(picture);
			}
		}

		if(applicationConfig.isEnablePictureProducerTopicExchange()) {

			// publish to exchange, TOPIC EXCHANGE
			// multiple queues are binded to exchange, send messages only to matching routing key.
			// routing key is in format *.*.* -> * can be any word
			for(int i=1; i<=5; i++) {
				Picture picture = new Picture();

				picture.setName("name "+i);
				picture.setSize(ThreadLocalRandom.current().nextLong(1, 10001));
				picture.setSource(SOURCES.get(i % SOURCES.size()));
				picture.setType(TYPES.get(i % TYPES.size()));


				pictureProducerTopicExchange.sendMessage(picture);
			}
		}
	}
}
