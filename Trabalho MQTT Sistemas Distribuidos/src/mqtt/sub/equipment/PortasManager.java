package mqtt.sub.equipment;

import org.eclipse.paho.client.mqttv3.MqttMessage;

import mqtt.sub.EquipmentManager;

public class PortasManager extends EquipmentManager{
	
	private final String[] topics = {TOPIC_RELOGIO};

	private double relogio = 0.0;
	
	public PortasManager() {
		super("Portas_Client");
		subscribe(topics);
	}

	public PortasManager(String ip, String port, String clientId) {
		super(ip, port, clientId);
		subscribe(topics);
	}

	public PortasManager(String ip, String port) {
		super(ip, port);
		subscribe(topics);
	}
	
	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		System.out.println("Alterado: " + topic + " " + message);
		if(topic.equals(TOPIC_RELOGIO))
			this.relogio = Double.parseDouble(message.toString());
		if(relogio>22 || relogio<6) {
			status = true;
			System.out.println(this.getClass().getName() + "STATUS ABERTO");
		}
		else {
			status = false;
			System.out.println(this.getClass().getName() + "STATUS ABERTO");
		}
	}
		
	public double getRelogio() {
		return relogio;
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		PortasManager s = new PortasManager();
//		s.subscribe("test");
	}
	
}
