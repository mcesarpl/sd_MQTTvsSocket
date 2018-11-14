import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Subscriber implements MqttCallback {

	private MqttClient client;
	private String broker;
	
	public Subscriber(String ip, String port) {
		this.broker = "tcp://"+ip+":" + port;
	}

	public static void main(String[] args) {
		Subscriber s = new Subscriber("iot.eclipse.org", "1883");
		s.subscribe("test");
	}

	public void subscribe(String topic) {
		try {
			client = new MqttClient(broker, "Sending");
			client.connect();
			client.setCallback(this);
			client.subscribe(topic);
			MqttMessage message = new MqttMessage();
			message.setPayload("A single message from my computer fff".getBytes());
			client.publish(topic,message);
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void connectionLost(Throwable cause) {
		// TODO Auto-generated method stub

	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		System.out.println(message);
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		// TODO Auto-generated method stub

	}
}