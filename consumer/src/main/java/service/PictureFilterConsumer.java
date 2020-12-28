package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Picture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PictureFilterConsumer {

    ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = "q.picture.filter")
    public void listen(String pictureMessage) {
        try {
            Picture picture = objectMapper.readValue(pictureMessage, Picture.class);
            log.info("filter queue - the sent mobile image picture is: {}", picture);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
