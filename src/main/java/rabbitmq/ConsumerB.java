package rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class ConsumerB implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.out.println("消费者B收到消息"+new String(message.getBody()));
    }
}
