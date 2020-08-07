package rabbitmq.m3_publishsubscribe;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer {
    public static void main(String[] args) throws Exception{
        ConnectionFactory f = new ConnectionFactory();
        f.setHost("192.168.126.131");
        f.setPort(5672);
        f.setUsername("jack");
        f.setPassword("666666");
        Connection c = f.newConnection();
        Channel ch = c.createChannel();
        ch.exchangeDeclare("logs","fanout");
        String queueName = ch.queueDeclare().getQueue();
        ch.queueBind(queueName,"logs","");
        System.out.println("等待接收数据");

        DeliverCallback callback = new DeliverCallback() {
            @Override
            public void handle(String s, Delivery delivery) throws IOException {
                String msg = new String (delivery.getBody(),"UTF-8");
                System.out.println("收到:"+msg);
            }
        };
        CancelCallback cancal = new CancelCallback() {
            @Override
            public void handle(String s) throws IOException {

            }
        };
        ch.basicConsume(queueName,true,callback,cancal);
    }
}
