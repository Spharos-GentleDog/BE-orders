package egenius.orders.global.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@EnableKafka // @KafkaListener를 위해 사용
@Configuration
public class KafkaProducerConfig {

    // yml파일에 설정한 bootstrap 주소 -> broker 주소를 의미
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;


    /**
     * Producer 설정
     */

    // Producer 생성 -> 매번 새로운 producer를 생성하는건 부하가 크기에 factory를 사용해서 같은 producer 인스턴스를 재사용
    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> configs = new HashMap<>();
        // BootStrap 서버주소
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        // Key와 Value의 직렬화에 사용할 클래스를 설정 -> 둘 다 문자열 데이터이므로 StringSerializer 사용
        // 만약 int 데이터라면 -> IntegerSerializer를 사용하면 됨
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(configs);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        // 앞서 생성한 producerFactory를 인수로 넣음
        return new KafkaTemplate<>(producerFactory());
    }

    // 강한 결합을 없애기 위해 Producer사용
    @Bean
    public Producer<String, String> createProducer() {
        Properties properties = new Properties();
        // BootStrap 서버주소
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        // Key와 Value의 직렬화에 사용할 클래스를 설정 -> 둘 다 문자열 데이터이므로 StringSerializer 사용
        // 만약 int 데이터라면 -> IntegerSerializer를 사용하면 됨
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new KafkaProducer<>(properties);
    }

}
