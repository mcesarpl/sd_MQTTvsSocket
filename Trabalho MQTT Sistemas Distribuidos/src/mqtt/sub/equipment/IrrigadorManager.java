package mqtt.sub.equipment;

import org.eclipse.paho.client.mqttv3.MqttMessage;

import mqtt.sub.EquipmentManager;

public class IrrigadorManager extends EquipmentManager{
	
	private final String[] topics = {TOPIC_UMIDADE};
	
	private int umidade = 60;
	
	public IrrigadorManager() {
		super("Irrigador_Client");
		subscribe(topics);
	}

	public IrrigadorManager(String ip, String port, String clientId) {
		super(ip, port, clientId);
		subscribe(topics);
	}

	public IrrigadorManager(String ip, String port) {
		super(ip, port);
		subscribe(topics);
	}
	

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		System.out.println("Alterado: " + topic + " " + message);
		if(topic.equals(TOPIC_UMIDADE))
			this.umidade = Integer.parseInt(message.toString());
		if(umidade <= 30) {
			status = true;
			System.out.println(this.getClass().getName() + "STATUS ABERTO");
		}
		else {
			status = false;
			System.out.println(this.getClass().getName() + "STATUS ABERTO");
		}
	}
	
	
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		IrrigadorManager s = new IrrigadorManager();
//		s.subscribe("test");
	}
	
}
