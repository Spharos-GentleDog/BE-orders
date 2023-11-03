//package egenius.orders.global.config.kafka;
//
//import org.apache.kafka.clients.admin.AdminClientConfig;
//import org.apache.kafka.clients.admin.NewTopic;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.consumer.KafkaConsumer;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.core.*;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Properties;
//
//@EnableKafka // @KafkaListener를 위해 사용
//@Configuration
//public class KafkaConfig {
//
//    // yml파일에 설정한 bootstrap 주소
//    @Value("${spring.kafka.bootstrap-servers}")
//    private String bootstrapAddress;
//
//     //Kafka 클러스터(broker의 모음집이라 생각하면 됨)를 관리하기 위한 설정
//     //이를 통해 토픽의 생성,관리,조회 등 클러스터의 전반적인 관리를 할 수 있음
//    @Bean
//    public KafkaAdmin kafkaAdmin() {
//        Map<String, Object> configs = new HashMap<>();
//        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
//        return new KafkaAdmin(configs);
//    }
//
//
//    /**
//     * Producer 설정
//     */
//
//    // 토픽 생성 -> (토픽 이름, 할당할 파티션, replication 수) 를 입력하여 생성
//    @Bean
//    public NewTopic paymentTopic() {
//        return new NewTopic("payment_data", 1, (short) 1);
//    }
//
//
//    // Producer 관련 설정
//    @Bean
//    public ProducerFactory<String, String> producerFactory() {
//        Map<String, Object> configProps = new HashMap<>();
//        // BootStrap 서버주소
//        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
//        // Key와 Value의 직렬화에 사용할 클래스를 설정 -> 둘 다 문자열 데이터이므로 StringSerializer 사용
//        // 만약 int 데이터라면 -> IntegerSerializer를 사용하면 됨
//        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        return new DefaultKafkaProducerFactory<>(configProps);
//    }
//
//    @Bean
//    public KafkaTemplate<String, String> kafkaTemplate() {
//        // 앞서 생성한 producerFactory를 인수로 넣음
//        return new KafkaTemplate<>(producerFactory());
//    }
//
//
//    // ------------ //
//    // Consumer 설정
//    @Bean
//    public ConsumerFactory<String, String> consumerFactory() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test1");
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//
//        return new DefaultKafkaConsumerFactory<>(props);
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, String> factory
//                = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        return factory;
//    }
//
//
//    @Bean
//    public KafkaConsumer createConsumer() {
//        Properties configs = new Properties();
//        // 데이터를 가져올 브로커를 선택
//        configs.put("bootstrap.servers", bootstrapAddress); // 실제 연동시에는 2개 이상의 브로커 정보를 넣는것이 좋다
//        configs.put("group.id", "test_group");
//        // key, value에 대한 직렬화 설정 -> Byte array, String, Integer Serializer를 사용할 수 있다
//        // 메시지를 가져올 파티션의 key를 역직렬화
//        configs.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        // value는 메시지라 보면 됨
//        configs.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//
//        return new KafkaConsumer<>(configs);
//    }
//}
