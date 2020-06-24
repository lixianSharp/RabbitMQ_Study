package org.lixianyuan.ampq.controller;

import org.lixianyuan.ampq.bean.Book;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lxy
 * @Date 2020/5/23
 * @Descript
 **/
@RestController
public class RabbitMQController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    //AmqpAdmin:创建和删除 Queue，Exchange，Binding
    @RequestMapping(value = "/amqpAdmin",method = RequestMethod.POST)
    public void createExchange(){
//        amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));
//        System.out.println("创建完成");

//        amqpAdmin.declareQueue(new Queue("amqpadmin.queue",true));
//        System.out.println("创建完成");
        //创建绑定规则 (将交换器通过某个路由键和队列绑定。注意：一个交换器可以绑定多个队列，一个队列可以和多个交换器绑定，是多对多的关系)
        amqpAdmin.declareBinding(new Binding("amqpadmin.queue",Binding.DestinationType.QUEUE,"amqpadmin.exchange","ampq.haha",null));
        System.out.println("绑定完成");
    }



    /**
     * 1、单播（点对点）
     */
    @RequestMapping(value = "/testmq",method = RequestMethod.POST)
    public String  contextLoads() {
        //Message 需要自己构造一个；定义消息体内容和消息头
        //rabbitTemplate.send(String routingKey, Message message)

        //object默认当成消息体，只需要传入要发送的对象，自动序列化发送给rabbitmq;
        //rabbitTemplate. convertAndSend(String exchange, String routingKey, Object object, @Nullable CorrelationData correlationData);
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("msg", "这是第一个消息");
        map.put("data", Arrays.asList("helloworld", 123, true, "李贤元"));
        //表示：给和名字为atguigu.new的路由键绑定的 名字为 exchange.direct的交换器发送消息，
        // 至于消息会发送到哪里，由绑定了atguigu.news路由键的exchange.direct交换器绑定了哪个队列决定，绑定了哪个队列消息就发送到哪个队列中
        //对象被默认序列化以后发送
        rabbitTemplate.convertAndSend("exchange.direct","atguigu.news",map);
        return "ok";
    }

    //接收数据
    @RequestMapping(value = "/receive",method = RequestMethod.POST)
    public String  receive(){
        //接收名字为 atguigu.news 的队列中的数据
        Object o = rabbitTemplate.receiveAndConvert("atguigu.news");
        System.out.println(o.getClass());
        System.out.println(o);
        return "ok";
    }



    /**
     * 1、单播（点对点）
     */
    @RequestMapping(value = "/testmq2",method = RequestMethod.POST)
    public String  contextLoads2() {
        for(int i=1;i<=5;i++){
            rabbitTemplate.convertAndSend("exchange.direct","atguigu.news",new Book("红楼梦"+i,"曹雪芹"+i));
        }

        return "ok";
    }


    /**
     * 1、广播
     */
    @RequestMapping(value = "/sendMsg",method = RequestMethod.POST)
    public String  sendMsg() {

        rabbitTemplate.convertAndSend("exchange.fanout","",new Book("红楼梦","曹雪芹"));
        return "ok";
    }
}
