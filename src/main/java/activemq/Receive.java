package activemq;

import java.util.ArrayList;
import java.util.List;

import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.StreamMessage;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Receive {
	
	/**消息服务器连接地址**/
	private static final String brokerURL="tcp://135.149.96.4:6161";
	/**消息目的地**/
	private static final String queue_name="message";
	
	public static void main(String[] args) {
		receive();
	}
	/**
	 * 接受消息
	 */
	public static void  receive(){
		//创建连接消息服务器的连接工厂
		ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory(brokerURL);
		//发送者发送的所有包下的对象都信任接收
		//cf.setTrustAllPackages(true);
		//发送者发送的某些包下的对象信任接收
		List<String> trustedPackages=new ArrayList<String>();
		trustedPackages.add("activemq");
		cf.setTrustedPackages(trustedPackages);
		//创建连接
		Connection conn = null;
		Session session= null;
		MessageConsumer createConsumer = null;
		try {
			conn = cf.createConnection();
			// true or false 表示是否支持事务 ,Session.AUTO_ACKNOWLEDGE为自动确认，客户端发送和接收消息不需要做额外的工作。
			session=conn.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
			//创建目的地
			Destination destination = session.createQueue(queue_name);
			//消息的选择器
			String sele="";
			//创建消费者
			//createConsumer = session.createConsumer(destination);
			//创建有选择器的消费者
			createConsumer = session.createConsumer(destination,sele);
			//接收对象消息时，需要重启连接
			conn.start();
			//消费者接收消息
			
			/*while(true){
				//receive()方法同步方式接收消息
				Message receive = createConsumer.receive();*/
				
				//异步接受，使用消息监听器接收
				/*createConsumer.setMessageListener(new MessageListener(){
                    //监听器的回调方法，当监听器坚挺到消息后，会将消息传到此方法
					public void onMessage(Message receive) {
						// TODO Auto-generated method stub
						if(receive instanceof TextMessage){
							try {
								String message = ((TextMessage)receive).getText();
								System.out.println(message);
							} catch (JMSException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}else if(receive instanceof ObjectMessage){
							try {
								Flow flow = (Flow) ((ObjectMessage)receive).getObject();
								System.out.println(flow.toString());
							} catch (JMSException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}else if(receive instanceof MapMessage){
							try {
								boolean boolean1 = ((MapMessage)receive).getBoolean("key1");
								double double1 = ((MapMessage)receive).getDouble("key2");
								String string = ((MapMessage)receive).getString("key3");
								System.out.println(boolean1+":"+double1+":"+string);
							} catch (JMSException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							 
						}else if(receive instanceof BytesMessage){
							try {
								boolean readBoolean = ((BytesMessage)receive).readBoolean();
								double readDouble = ((BytesMessage)receive).readDouble();
								String readUTF = ((BytesMessage)receive).readUTF();
								System.out.println(readBoolean+":"+readDouble+":"+readUTF);
							} catch (JMSException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}else if(receive instanceof StreamMessage){
							try {
								boolean readBoolean = ((StreamMessage)receive).readBoolean();
								long readLong = ((StreamMessage)receive).readLong();
								String readString = ((StreamMessage)receive).readString();
								System.out.println(readBoolean+":"+readLong+":"+readString);
							} catch (JMSException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
					}
					
				});*/
			
			createConsumer.setMessageListener(new MyListener());
				//receive.acknowledge();
				//接收事务消息
				//session.commit();
				
			//}
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} /*finally{
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
		}*/
	} 

}
