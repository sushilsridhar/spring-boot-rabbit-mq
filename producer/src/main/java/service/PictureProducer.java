package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Picture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureProducer {

    @Autowired
    RabbitTemplate rabbitTemplate;

    private Logger log = LoggerFactory.getLogger(PictureProducer.class);

    ObjectMapper objectMapper = new ObjectMapper();

    public void sendMessage(Picture picture) {

        try {
            String json = objectMapper.writeValueAsString(picture);
            rabbitTemplate.convertAndSend("x.picture", picture.getType(), json);
            log.info("published picture {}", json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
