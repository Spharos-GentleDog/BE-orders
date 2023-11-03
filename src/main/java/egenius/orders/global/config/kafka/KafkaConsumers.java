//package egenius.orders.global.config.kafka;
//
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.apache.kafka.clients.consumer.ConsumerRecords;
//import org.apache.kafka.clients.consumer.KafkaConsumer;
//import org.springframework.beans.factory.annotation.Value;
//
//import java.util.Arrays;
//import java.util.Properties;
//
//public class KafkaConsumers {
//    // yml파일에 설정한 bootstrap 주소
//    @Value("${spring.kafka.bootstrap-servers}")
//    private String bootstrapAddress;
//
//    public void consumMessage() {
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
//        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(configs);
//        // 데이터를 가져올 토픽을 선택
//        // subscribe = 토픽의 전체 파티션에서 가져옴
//        consumer.subscribe(Arrays.asList("test1"));
//        // assign = 토픽에서 특정 파티션 내용만 가져올 수 있음 -> key가 존재한다면 다음처럼 사용할 수 있다
//            // TopicPartition partition0 = new TopicPartition("토픽이름", 파티션 key);
//            // TopicPartition partition1 = new TopicPartition("토픽이름", 파티션 key);
//            // cosumer.assign(Arrays.asList(partition0, partition1));
//
//        // 데이터를 가져오는 polling구문
//        // -> 컨슈머가 허락하는 한 많은 데이터를 읽는 것
//        while (true) {
//            // poll에서 설정한 ms동안 데이터를 기다린다 = 설정한 값동안 기다리다가 다음 코드를 실행한다
//            // 만약 그 시간동안 데이터가 오지 않는다면 -> 빈 records를 반환, 데이터가 있다면 데이터 records를 반환
//            ConsumerRecords<String, String> records = consumer.poll(500);
//            // records는 데이터 list이므로, record로 뽑아낸 후 record.value()로 프로듀서가 보낸 진짜 값을 가져올 수 있다
//            for (ConsumerRecord<String, String> record : records) {
//                System.out.println("record.value() = " + record.value());
//            }
//        }
//
//    }
//}
