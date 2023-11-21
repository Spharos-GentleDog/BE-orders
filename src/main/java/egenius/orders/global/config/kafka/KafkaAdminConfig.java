package egenius.orders.global.config.kafka;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaAdminConfig {

    // yml파일에 설정한 bootstrap 주소 -> broker 주소를 의미
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    //Kafka 클러스터(broker의 모임이라 생각하면 됨)를 관리하기 위한 설정
    //이를 통해 브로커,토픽,파티션의 생성,관리,조회 등 클러스터의 전반적인 관리를 할 수 있음
    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    // 토픽 생성 -> (토픽 이름, 할당할 파티션, replication 수) 를 입력하여 생성
    // kafkaAdmin을 bean등록 해두었다면, topic을 bean등록 하는순간 Admin에서 자동으로 브로커에 토픽을 추가해준다
    @Bean
    public NewTopic paymentTopic() {
        // 메시지 보관주기 설정 -> 20h = 7.2*10^6 ms
        Map<String, String> configs = new HashMap<>();
        configs.put(TopicConfig.RETENTION_MS_CONFIG, "72000000");
        // 토픽 생성
        return TopicBuilder
                .name("payment_topic")
                .partitions(1)
                .replicas(1)
                .configs(configs)
                .build();
    }

}

