package rabbitmq.m2_work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.Scanner;

public class Producer {
    public static void main(String[] args) throws Exception{
        ConnectionFactory f = new ConnectionFactory();
        f.setHost("192.168.126.131");
        f.setUsername("jack");
        f.setPassword("666666");
        Connection c = f.newConnection();
        Channel ch = c.createChannel();
        ch.queueDeclare("helloworld",false,false,false,null);
        while (true){
            System.out.print("输入消息");
            String msg = new Scanner(System.in).nextLine();
            if ("exit".equals(msg)){
                break;
            }
            ch.basicPublish("","helloworld",null,msg.getBytes());
            System.out.println("消息已发送"+msg);
        }
        c.close();
    }
}
