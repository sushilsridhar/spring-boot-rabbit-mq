package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Picture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class PictureImageConsumer {

    ObjectMapper objectMapper = new ObjectMapper();

    private Logger log = LoggerFactory.getLogger(PictureImageConsumer.class);

    @RabbitListener(queues = "q.picture.image")
    public void listen(String pictureMessage) {
        try {
            Picture picture = objectMapper.readValue(pictureMessage, Picture.class);
            log.info("image queue - the sent jpg/png image picture is: {}", picture);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
