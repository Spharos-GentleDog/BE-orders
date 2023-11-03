//package egenius.orders.global.config.kafka;
//
//import org.apache.kafka.clients.producer.KafkaProducer;
//import org.apache.kafka.clients.producer.ProducerRecord;
//import org.springframework.beans.factory.annotation.Value;
//
//import java.io.IOException;
//import java.util.Properties;
//
//public class KafKaProducers {
//    // yml파일에 설정한 bootstrap 주소
//    @Value("${spring.kafka.bootstrap-servers}")
//    private String bootstrapAddress;
//
//    public void sendMessage() throws IOException {
//        Properties configs = new Properties();
//        // 브로커 정보 입력
//        configs.put("bootstrap.servers", bootstrapAddress); // 실제 연동시에는 2개 이상의 브로커 정보를 넣는것이 좋다
//        // key, value에 대한 직렬화 설정 -> Byte array, String, Integer Serializer를 사용할 수 있다
//        // key는 메시지를 보낼 때, 토픽의 파티션이 지정될 때 사용
//        configs.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        // value는 메시지라 보면 됨
//        configs.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//
//        // 카프카 프로듀서 객체 생성
//        KafkaProducer<String, String> producer = new KafkaProducer<>(configs);
//        // 전송할 객체, 즉 메시지라 보면 된다
//        // (topic, "key", value) 순서로 설정할 수 있고, key를 생략할 수 있다 -> 파라미터 수에 따라 자동으로 오버라이딩됨
//        // key를 넣지 않으면 null로 들어가게되고, 이때는 파티션의 수에 따라서 round-robin으로 들어가게 된다
//        ProducerRecord record = new ProducerRecord("test1", "test성공");
//        // 전송
//        producer.send(record);
//        // 프로듀서 종료
//        producer.close();
//    }
//}
