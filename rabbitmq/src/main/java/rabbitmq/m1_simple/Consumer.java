package rabbitmq.m1_simple;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer {
    public static void main(String[] args) throws Exception {
        // 连接工厂
        ConnectionFactory f = new ConnectionFactory();
        f.setHost("192.168.126.131");
        f.setUsername("jack");
        f.setPassword("666666");
        // 建立连接
        Connection c = f.newConnection();
        // 建立信道
        Channel ch = c.createChannel();
        // 声明队列,如果该队列建过,则不会重复建立
        ch.queueDeclare("Helloworld",false,false,false,null);
        System.out.println("等待接收数据");
        // 收到消息后用来处理消息的回调对象
        DeliverCallback callback = new DeliverCallback() {
            @Override
            public void handle(String consumerTag, Delivery message) throws IOException {
                String msg = new String(message.getBody(), "UTF-8");
                System.out.println("收到: "+msg);
            }
        };
        // 消费者取消时的回调对象
        CancelCallback cancel = new CancelCallback() {
            @Override
            public void handle(String consumerTag) throws IOException {
            }
        };

        ch.basicConsume("Helloworld", true, callback, cancel);
    }
}
