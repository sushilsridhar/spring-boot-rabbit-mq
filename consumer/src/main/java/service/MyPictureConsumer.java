package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Picture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MyPictureConsumer {

    ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = "q.mypicture.image")
    public void listen(String pictureMessage) throws JsonProcessingException {

        Picture picture = objectMapper.readValue(pictureMessage, Picture.class);

        if(picture.getSize() > 9000) {
            throw new IllegalArgumentException("Picture size is too large: "+ picture);
        }

        log.info("image queue - the sent jpg/png image picture is: {}", picture);
    }
}
