package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class ScheduledConsumer {

    private Logger log = LoggerFactory.getLogger(ScheduledConsumer.class);

    @RabbitListener(queues = "fixedRate", concurrency = "3")
    public void printMessage(String message) {
        log.info("consumed {} on thread {} ", message, Thread.currentThread().getName() );

        try {
            Thread.sleep(ThreadLocalRandom.current().nextLong(2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
