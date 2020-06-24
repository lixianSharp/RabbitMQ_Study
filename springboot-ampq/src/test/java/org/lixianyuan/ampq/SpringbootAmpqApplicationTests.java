package org.lixianyuan.ampq;





import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class SpringbootAmpqApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 1、单播（点对点）
     */
    @Test
    public void contextLoads() {
        //Message 需要自己构造一个；定义消息体内容和消息头
        //rabbitTemplate.send(String routingKey, Message message)

        //object默认当成消息体，只需要传入要发送的对象，自动序列化发送给rabbitmq;
        //rabbitTemplate. convertAndSend(String exchange, String routingKey, Object object, @Nullable CorrelationData correlationData);
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("msg", "这是第一个消息");
        map.put("data", Arrays.asList("helloworld", 123, true, "李贤元"));
        //表示：给和名字为atguigu.new的路由键绑定的 名字为 exchange.direct的交换器发送消息，
        // 至于消息会发送到哪里，由绑定了atguigu.news路由键的exchange.direct交换器绑定了哪个队列决定，绑定了哪个队列消息就发送到哪个队列中
        rabbitTemplate.convertAndSend("exchange.direct","atguigu.news",map);
    }

    @Test
    public void test(){
        System.out.println("sdf");
    }

}
