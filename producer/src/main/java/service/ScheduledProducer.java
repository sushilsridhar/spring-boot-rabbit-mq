package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledProducer {

    @Autowired
    RabbitTemplate rabbitTemplate;

    private int i = 0;

    private Logger log = LoggerFactory.getLogger(ScheduledProducer.class);

    @Scheduled(fixedRate = 500)
    public void sendHello() {
        log.info("i is "+ i++);
        rabbitTemplate.convertAndSend("fixedRate", "hello rabbit " + i);
    }
}
