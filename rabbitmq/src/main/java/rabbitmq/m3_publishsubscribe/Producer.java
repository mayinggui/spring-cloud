package rabbitmq.m3_publishsubscribe;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import javax.naming.ldap.ControlFactory;
import java.util.Scanner;

public class Producer {
    public static void main(String[] args) throws Exception {
        ConnectionFactory f = new ConnectionFactory();
        f.setHost("192.168.126.131");
        f.setPort(5672);
        f.setUsername("jack");
        f.setPassword("666666");
        Connection c = f.newConnection();
        Channel ch = c.createChannel();
        ch.exchangeDeclare("logs","fanout");
        while (true){
            System.out.println("输入消息:");
            String msg = new Scanner(System.in).nextLine();
            if("exit".equals(msg)){
                break;
            }
            ch.basicPublish("logs","",null,msg.getBytes("UTF-8"));
            System.out.println("消息已发送"+msg);
        }
        c.close();
    }
}
