package pub.huanxi.stream.demo;

import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import pub.huanxi.stream.demo.rocketmq.CustomerChannel;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MQApplication.class)
class MQApplicationTest {

    @Autowired
    private CustomerChannel customerChannel;

    @Test
    public void testStream() {
        String msg = "hello stream ...";
        this.customerChannel.output()
                .send(MessageBuilder.withPayload(msg).setHeader(RocketMQHeaders.TAGS, "tag3").build());
    }
}