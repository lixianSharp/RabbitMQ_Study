package org.lixianyuan.ampq.config;


import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lxy
 * @Date 2020/5/23
 * @Descript
 **/
@Configuration
public class MyAMQPConfig {

    @Bean
    public MessageConverter messageConverter(){
        //让消息以json的格式进行发送和接收
        return new Jackson2JsonMessageConverter();
    }
}
