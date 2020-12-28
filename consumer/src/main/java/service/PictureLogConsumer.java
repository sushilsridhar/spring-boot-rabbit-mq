package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Picture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PictureLogConsumer {

    ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = "q.picture.log")
    public void listen(String pictureMessage) {
        try {
            Picture picture = objectMapper.readValue(pictureMessage, Picture.class);
            log.info("log queue - the sent large image picture is: {}", picture);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
