package config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@ConditionalOnProperty(prefix = "app", name = "enableScheduledProducer", havingValue = "true", matchIfMissing = true)
@Configuration
@EnableScheduling //--> Scheduled publishing
public class ScheduledProducerConfig {

    // this class is just to enable/disable bean of @EnableScheduling, alternatively this annotation can be added in main class
}
