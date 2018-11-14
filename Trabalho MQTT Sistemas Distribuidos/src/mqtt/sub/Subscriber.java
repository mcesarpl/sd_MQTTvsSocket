package mqtt.sub;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import mqtt.MQTTElement;

public abstract class Subscriber extends MQTTElement implements MqttCallback {

	private static final String DEFAULT_CLIENT_ID = "Subscriber_Manager";
	
	public Subscriber(String clientId) {
		super(clientId);
	}
		
	public Subscriber(String ip, String port) {
		this(ip, port, DEFAULT_CLIENT_ID);
	}
	
	public Subscriber(String ip, String port, String clientId) {
		super(DEFAULT_QOS,ip, port,clientId);
	}
	
	public void conectar() throws MqttException {
		cliente = new MqttClient(broker, clientId);
		System.out.println("Conectando-se ao broker: " + broker);
		cliente.connect();
		System.out.println("Conectado");
	}
	
	public void inscrever(String topic) throws MqttException {
		cliente.setCallback(this);
		cliente.subscribe(topic);
//		MqttMessage message = new MqttMessage();
//		message.setPayload("A single message from my computer fff".getBytes());
//		cliente.publish(topic,message);
	}
	
	public void disconnect() throws MqttException {
		cliente.disconnect();
	}
	
	public void subscribe(String... topics) {
		try {
			conectar();
			for(String t : topics)
				inscrever(t);
			
		} catch (MqttException e) {
			e.printStackTrace();
		}
  
	}
	
}