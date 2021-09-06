package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Picture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MyPictureProducer {

    @Autowired
    RabbitTemplate rabbitTemplate;

    ObjectMapper objectMapper = new ObjectMapper();

    public void sendMessage(Picture picture) throws JsonProcessingException {

        String json = objectMapper.writeValueAsString(picture);
        rabbitTemplate.convertAndSend("x.mypicture", "", json);
        log.info("published my picture {}", json);
    }
}
