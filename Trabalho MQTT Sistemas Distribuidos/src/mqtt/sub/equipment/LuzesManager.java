package mqtt.sub.equipment;

import org.eclipse.paho.client.mqttv3.MqttMessage;

import mqtt.sub.EquipmentManager;

public class LuzesManager extends EquipmentManager{
	
	private final String[] topics = {TOPIC_PRESENCA, TOPIC_RELOGIO};
	
	private boolean presenca = false;
	private double relogio = 0.0;
	
	public LuzesManager() {
		super("Luzes_Client");
		subscribe(topics);
	}

	public LuzesManager(String ip, String port, String clientId) {
		super(ip, port, clientId);
		subscribe(topics);
	}

	public LuzesManager(String ip, String port) {
		super(ip, port);
		subscribe(topics);
	}
	

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		System.out.println("Alterado: " + topic + " " + message);
		if(topic.equals(TOPIC_PRESENCA))
			this.presenca = Boolean.parseBoolean(message.toString());
		else
			if(topic.equals(TOPIC_RELOGIO))
				this.relogio = Double.parseDouble(message.toString());
		if(presenca==true && (relogio>=18 || relogio<5)) {
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

	public double getRelogio() {
		return relogio;
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		LuzesManager s = new LuzesManager();
//		s.subscribe("test");
	}
	
}
