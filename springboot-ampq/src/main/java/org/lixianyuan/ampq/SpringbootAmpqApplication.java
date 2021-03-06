package org.lixianyuan.ampq;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * 自动配置
 * 1、RabbitAutoConfiguration
 * 2、有自动配置了连接工厂 ConnectionFactory
 * 3、RabbitProperties封装了 RabbitMQ的配置
 * 4、RabbitTemplate：给RabbitMQ发送和接收消息
 * 5、AmqAdmin：RabbitMQ系统管理功能组件
 *      AmqAdmin:创建和删除 Queue，Exchange，Binding
 * 6、@EnableRabbit+ @RabbitListener 监听消息队列的内容
 */
@EnableRabbit //开启基于注解的RabbitMQ模式
@SpringBootApplication
public class SpringbootAmpqApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAmpqApplication.class, args);
    }

}
