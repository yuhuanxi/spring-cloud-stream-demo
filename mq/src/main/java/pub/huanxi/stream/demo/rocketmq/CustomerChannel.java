package pub.huanxi.stream.demo.rocketmq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface CustomerChannel {

    /**
     * 这里的名称对应了spring.cloud.stream.rocketmq.bindings.<channelName>
     */
    String OUTPUT = "my-output";
    String INPUT = "my-input";

    @Output(CustomerChannel.OUTPUT)
    MessageChannel output();

    @Input(CustomerChannel.INPUT)
    SubscribableChannel input();
}
