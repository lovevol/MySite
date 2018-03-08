package rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class ConsumerA implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.out.println("消费者A收到消息"+new String(message.getBody()));
    }
}
