package pub.huanxi.stream.demo.controller;

import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pub.huanxi.stream.demo.rocketmq.CustomerChannel;

@RestController
@EnableBinding({CustomerChannel.class})
public class TestController {

    private final CustomerChannel customerChannel;

    public TestController(CustomerChannel customerChannel) {
        this.customerChannel = customerChannel;
    }

    /**
     * 使用一个controller断点模拟发送消息，可以在setHeader方法中设置header来实现消息过滤
     */
    @GetMapping("/message-send")
    public String testCustomInterfaceSendMsg() {
        Message<String> message = MessageBuilder.withPayload("send message")
                .setHeader(RocketMQHeaders.TAGS, "tag2")
                .setHeader("mytag", "my-tag")
                .build();

        this.customerChannel.output().send(message);

        Message<String> message2 = MessageBuilder.withPayload("send message")
                .setHeader(RocketMQHeaders.TAGS, "tag3")
                .setHeader("mytag", "your-tag")
                .build();

        this.customerChannel.output().send(message2);

        return "success";
    }

    /**
     * 使用@StreamListener来监听消息
     */
    @StreamListener(value = CustomerChannel.INPUT, condition = "headers['mytag']=='my-tag'")
    public void testCustomListener(Message message) {
        System.out.println(message.getHeaders().get("mytag") + " " + message.getPayload().toString());
    }

    /**
     * 使用@StreamListener来监听消息
     */
    @StreamListener(value = CustomerChannel.INPUT, condition = "headers['mytag']=='your-tag'")
    public void testCustomListenerFilter(Message message) {
        System.out.println(message.getHeaders().get("mytag") + " " + message.getPayload().toString());
    }
}
