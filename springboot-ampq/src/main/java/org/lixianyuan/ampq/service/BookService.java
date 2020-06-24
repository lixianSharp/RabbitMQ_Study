package org.lixianyuan.ampq.service;

import org.lixianyuan.ampq.bean.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


/**
 * @author lxy
 * @Date 2020/5/23
 * @Descript
 **/
@Service
public class BookService {


    //从队列中取消息的顺序是按照队列的结构来的，队列是先进先出，所以第一个取到的数据是最先进入队列的数据，一次类推。
    //所以取数据是有顺序的
    //监听名字为 atguigu.news 的队列名称，只要队列一有数据，就执行这个方法
    @RabbitListener(queues = "atguigu.news")
    public void receive(Book book){
        System.out.println("收到消息:"+book);//收到消息:Book(bookName=红楼梦, auth=曹雪芹)
    }


    @RabbitListener(queues = "atguigu")
    public void receive02(Message message){
        System.out.println(message.getBody());
        System.out.println(message.getMessageProperties());
        /**
         * [B@3675b2b8
         * MessageProperties [headers={__TypeId__=org.lixianyuan.ampq.bean.Book}, contentType=application/json, contentEncoding=UTF-8, contentLength=0, receivedDeliveryMode=PERSISTENT, priority=0, redelivered=false, receivedExchange=exchange.fanout, receivedRoutingKey=, deliveryTag=3, consumerTag=amq.ctag-effCbgXumRpDDe5_lNCHQg, consumerQueue=atguigu]
         */
    }

}
