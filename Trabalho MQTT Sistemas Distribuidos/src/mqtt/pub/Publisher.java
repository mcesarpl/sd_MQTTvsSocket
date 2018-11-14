package mqtt.pub;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import mqtt.MQTTElement;

public abstract class Publisher  extends MQTTElement{

	private static final String DEFAULT_CLIENT_ID = "Publisher_Manager";
			
	public Publisher(String clientId) {
		super(clientId);
	}
	
	public Publisher(String ip, String port) {
		this(ip, port, DEFAULT_CLIENT_ID);
	}
	
	public Publisher(String ip, String port, String clientId) {
		super(DEFAULT_QOS,ip, port,clientId);
	}
	
	public void disconnect() throws MqttException {
		cliente.disconnect();
		System.out.println("Desconectado.");
		System.exit(0);
	}
	
	public void connect() throws MqttException {
		this.persistence = new MemoryPersistence();
		cliente = new MqttClient(broker, clientId, persistence);
		connOpts = new MqttConnectOptions();
		connOpts.setCleanSession(true);
		System.out.println("Conectando-se ao broker: " + broker);
		cliente.connect(connOpts);
		System.out.println("Conectado");
	}
	
	public void publicar(String topic,String content) throws MqttPersistenceException, MqttException {
		System.out.println("Publicando mensagem: " + content);
		MqttMessage message = new MqttMessage(content.getBytes());
		message.setQos(qos);
		cliente.publish(topic, message);
		System.out.println("Mensagem publicada");
	}
	
	public void rodar(String topic, String content) {
		
		try {
			connect();
			publicar(topic,content);
			disconnect();
		} catch (MqttException me) {
			System.out.println("reason " + me.getReasonCode());
			System.out.println("msg " + me.getMessage());
			System.out.println("loc " + me.getLocalizedMessage());
			System.out.println("cause " + me.getCause());
			System.out.println("excep " + me);
			me.printStackTrace();
		}
	}
	
//	public static void main(String args[]) {
//		Publisher p = new Publisher("192.168.1.103","1883");
//		p.rodar("test", "Comando de teste");
//	}
	
	
}
