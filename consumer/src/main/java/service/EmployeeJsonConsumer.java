package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class EmployeeJsonConsumer {

    private Logger log = LoggerFactory.getLogger(EmployeeJsonConsumer.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = "json")
    public void listen(String message) {
        try {
            Employee employee = objectMapper.readValue(message, Employee.class);
            log.info("employee is: "+ employee);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
