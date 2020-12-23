package com.rabbitmq.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import service.EmployeeJsonProducer;
import service.HumanResourceProducer;
import service.PictureProducer;
import service.ScheduledProducer;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@ComponentScan("service")
@EnableScheduling
public class ProducerApplication implements CommandLineRunner {

	@Autowired
	private ScheduledProducer scheduledService;

	@Autowired
	private EmployeeJsonProducer employeeJsonProducer;

	@Autowired
	private HumanResourceProducer humanResourceProducer;

	@Autowired
	private PictureProducer pictureProducer;

	private final List<String> SOURCES = Arrays.asList("mobile", "web");
	private final List<String> TYPES = Arrays.asList("jpg", "png", "svg");


	public static void main(String[] args) {
		SpringApplication.run(ProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Scheduled publishing
		// scheduledService.sendHello();

		// Publishing in JSON format
		/*for(int i=1; i<=5; i++) {
			Employee employee = new Employee("name "+i, "empID "+i, LocalDate.now());
			employeeJsonProducer.sendMessage(employee);
		}*/

		// publish to exchange, FANOUT EXCHANGE
		/*for(int i=1; i<=5; i++) {
			Employee employee = new Employee("name "+i, "empID "+i, LocalDate.now());
			humanResourceProducer.publishToExchange(employee);
		}*/

		// publish to exchange, DIRECT EXCHANGE
		/*for(int i=1; i<=5; i++) {
			Picture picture = new Picture();

			picture.setName("name "+i);
			picture.setSize(ThreadLocalRandom.current().nextLong(1, 10001));
			picture.setSource(SOURCES.get(i % SOURCES.size()));
			picture.setType(TYPES.get(i % TYPES.size()));


			pictureProducer.sendMessage(picture);
		}*/

	}
}
