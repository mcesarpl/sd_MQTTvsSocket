package mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MQTTElement {

	public static final String TOPIC_PRESENCA = "presenca";
	public static final String TOPIC_TEMPERATURA = "temperatura";
	public static final String TOPIC_RELOGIO = "relogio";
	public static final String TOPIC_UMIDADE = "umidade";
	
	protected int qos;
	protected String broker;
	
	protected String clientId;
	protected static final int DEFAULT_QOS = 2;
	protected static final String DEFAULT_BROKER = "iot.eclipse.org";
	protected static final String DEFAULT_PORT = "1883";	
	
	protected MemoryPersistence persistence;
	protected MqttClient cliente;
	protected MqttConnectOptions connOpts;
	
	public MQTTElement(int qos, String ip, String port, String clientId) {
		this.qos = qos;
		this.broker = "tcp://"+ip+":" + port;
		this.clientId = clientId;
	}
	
	public MQTTElement(String broker, String port, String clientId) {
		this(DEFAULT_QOS, broker, port, clientId);
	}
	
	public MQTTElement(String clientId) {
		this(DEFAULT_QOS, DEFAULT_BROKER,DEFAULT_PORT, clientId);
	}
	

}
