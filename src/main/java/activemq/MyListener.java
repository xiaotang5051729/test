package activemq;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.StreamMessage;
import javax.jms.TextMessage;

public class MyListener implements MessageListener{

	public void onMessage(Message receive) {
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

}
