package activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class TopicReceive {
	/**消息服务器连接地址**/
	private static final String brokerURL="tcp://135.149.96.4:6161";
	/**消息目的地**/
	private static final String topic_name="message";
	
	public static void main(String[] args) {
		receive();
	}
	/**
	 * 接受消息
	 */
	public static void  receive(){
		//创建连接消息服务器的连接工厂
		ConnectionFactory cf = new ActiveMQConnectionFactory(brokerURL);
		//创建连接
		Connection conn = null;
		Session session= null;
		MessageConsumer createConsumer = null;
		try {
			conn = cf.createConnection();
			// true or false 表示是否支持事务 ,Session.AUTO_ACKNOWLEDGE为自动确认，客户端发送和接收消息不需要做额外的工作。
			session=conn.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
			//创建目的地
			Destination destination = session.createTopic(topic_name);
			//创建消费者
			createConsumer = session.createConsumer(destination);
			//接收消息时，需要重启连接
			conn.start();
			//消费者接收消息
			Message receive = createConsumer.receive();
			
			if(receive instanceof TextMessage){
				String message=((TextMessage)receive).getText();
				System.out.println(message);
			}
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				if(null!=conn){
					conn.close();
				}
				if(null!=session){
					session.close();
				}
				if(null!=createConsumer){
					createConsumer.close();
				}
			} catch (JMSException e) {
				// TODO: handle exception
			}
		}
	} 
}
