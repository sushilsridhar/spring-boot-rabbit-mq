package config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
@Getter
@Setter
public class ApplicationConfig {

    private boolean enableEmployeeJsonProducer;
    private boolean enableHumanResourceProducer;
    private boolean enablePictureProducer;
    private boolean enablePictureProducerTopicExchange;
    private boolean enableMyPictureProducer;
}
