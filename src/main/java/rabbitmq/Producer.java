package rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Producer {
    private static final String EXCHANGE_KEY = "mySite_exchange";
    private static final String QUEUE_KEY = "test_queue";
    @Autowired
    AmqpTemplate amqpTemplate;
     public void sendMessage(Object object){
        // convertAndSend 将Java对象转换为消息发送至匹配key的交换机中Exchange
         //amqpTemplate.convertAndSend(EXCHANGE_KEY, QUEUE_KEY, object);
         Message message = MessageBuilder.withBody(object.toString().getBytes()).build();
         amqpTemplate.send(EXCHANGE_KEY,QUEUE_KEY,message);
     }
}
