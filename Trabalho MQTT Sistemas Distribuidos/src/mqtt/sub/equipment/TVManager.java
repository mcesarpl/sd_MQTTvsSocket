package mqtt.sub.equipment;

import org.eclipse.paho.client.mqttv3.MqttMessage;

import mqtt.sub.EquipmentManager;

public class TVManager extends EquipmentManager{
	
	private final String[] topics = {TOPIC_PRESENCA};
	
	private boolean presenca = false;
	
	public TVManager() {
		super("TV_Client");
		subscribe(topics);
	}

	public TVManager(String ip, String port, String clientId) {
		super(ip, port, clientId);
		subscribe(topics);
	}

	public TVManager(String ip, String port) {
		super(ip, port);
		subscribe(topics);
	}
	


	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		System.out.println("Alterado: " + topic + " " + message);
		if(topic.equals(TOPIC_PRESENCA))
			this.presenca = Boolean.parseBoolean(message.toString());
		if(presenca == true) {
			status = true;
			System.out.println(this.getClass().getName() + "STATUS ABERTO");
		}
		else {
			status = false;
			System.out.println(this.getClass().getName() + "STATUS ABERTO");
		}
	}
	
	
	
	public boolean isPresenca() {
		return presenca;
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		TVManager s = new TVManager();
//		s.subscribe("test");
	}
	
}
