package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeJsonProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    private Logger log = LoggerFactory.getLogger(EmployeeJsonProducer.class);

    public void sendMessage(Employee employee) {

        try {
            String json = objectMapper.writeValueAsString(employee);
            rabbitTemplate.convertAndSend("json",json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        log.info("published data: "+ employee);
    }
}
