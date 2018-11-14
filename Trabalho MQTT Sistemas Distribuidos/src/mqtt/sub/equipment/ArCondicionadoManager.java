package mqtt.sub.equipment;

import org.eclipse.paho.client.mqttv3.MqttMessage;

import mqtt.sub.EquipmentManager;

public class ArCondicionadoManager extends EquipmentManager{
	
	private final String[] topics = {TOPIC_TEMPERATURA,TOPIC_PRESENCA};
	
	private float temperatura = 25.0f;
	private boolean presenca = false;
	
	public ArCondicionadoManager() {
		super("ArCondicionado_Client");
		subscribe(topics);
	}

	public ArCondicionadoManager(String ip, String port, String clientId) {
		super(ip, port, clientId);
		subscribe(topics);
	}

	public ArCondicionadoManager(String ip, String port) {
		super(ip, port);
		subscribe(topics);
	}
	


	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		System.out.println("Alterado: " + topic + " " + message);
		if(topic.equals(TOPIC_PRESENCA))
			this.presenca = Boolean.parseBoolean(message.toString());
		else
			if(topic.equals(TOPIC_TEMPERATURA))
				this.temperatura = Float.parseFloat(message.toString());
		if(temperatura>25f && presenca == true) {
			status = true;
			System.out.println(this.getClass().getName() + "STATUS ABERTO");
		}
		else {
			status = false;
			System.out.println(this.getClass().getName() + "STATUS FECHADO");
		}
	}
	
	public float getTemperatura() {
		return temperatura;
	}

	public boolean isPresenca() {
		return presenca;
	}
	

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		ArCondicionadoManager s = new ArCondicionadoManager();
//		s.subscribe("test");
	}
	
}
