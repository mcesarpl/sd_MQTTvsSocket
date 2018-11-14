import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Publisher {

	private int qos;
	private String broker;
	private String clientId;
	private MemoryPersistence persistence;

	public Publisher() {
		this.qos = 2;
		this.broker = "tcp://iot.eclipse.org:1883";
		this.clientId = "Manager";
		this.persistence = new MemoryPersistence();
	}
	
	public Publisher(String ip, String port) {
		this.qos = 2;
		this.broker = "tcp://"+ip+":" + port;
		this.clientId = "Manager";
		this.persistence = new MemoryPersistence();
	}
	
	
	public void publicar(String topic, String content) {
		try {
			MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
			MqttConnectOptions connOpts = new MqttConnectOptions();
			connOpts.setCleanSession(true);
			System.out.println("Connecting to broker: " + broker);
			sampleClient.connect(connOpts);
			System.out.println("Connected");
			System.out.println("Publishing message: " + content);
			MqttMessage message = new MqttMessage(content.getBytes());
			message.setQos(qos);
			sampleClient.publish(topic, message);
			System.out.println("Message published");
			sampleClient.disconnect();
			System.out.println("Disconnected");
			System.exit(0);
		} catch (MqttException me) {
			System.out.println("reason " + me.getReasonCode());
			System.out.println("msg " + me.getMessage());
			System.out.println("loc " + me.getLocalizedMessage());
			System.out.println("cause " + me.getCause());
			System.out.println("excep " + me);
			me.printStackTrace();
		}
	}
	
	public static void main(String args[]) {
		Publisher p = new Publisher();
		p.publicar("test", "Comando de teste");
	}
	
	
}
