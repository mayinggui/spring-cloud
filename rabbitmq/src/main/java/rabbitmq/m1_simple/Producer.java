package rabbitmq.m1_simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


public class Producer {

    public static void main(String[] args) throws Exception {
        //创建连接工厂,并设置连接信息
        ConnectionFactory f = new ConnectionFactory();
        f.setHost("192.168.126.131");
        f.setPort(5672); //5672是默认通信端口
        f.setUsername("jack");
        f.setPassword("666666");

        //与rabbit服务器建立连接
        Connection c = f.newConnection();
        //与rabbit服务器建立通信通道
        Channel channel = c.createChannel();

        //定义队列,会通知服务器想使用一个 "Helloworld" 队列,
        //服务器会找到这个队列，如果不存在，服务器会新建队列
        channel.queueDeclare("Helloworld",false,false,false,null);

        /**
         * 发布消息
         * 参数含义:
         * 		 * 	-exchange: 交换机名称,空串表示默认交换机"(AMQP default)",不能用 null
         * 		 * 	-routingKey: 对于默认交换机,路由键就是目标队列名称
         * 		 * 	-props: 其他参数,例如头信息
         * 		 * 	-body: 消息内容byte[]数组
         */

        channel.basicPublish("","Helloworld",null,"Hello啊 jack马~".getBytes());

        System.out.println("消息已发送");
        //关闭通道
        c.close();
    }
}