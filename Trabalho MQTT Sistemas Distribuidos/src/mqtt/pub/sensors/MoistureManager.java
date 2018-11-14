package mqtt.pub.sensors;

import org.eclipse.paho.client.mqttv3.MqttException;

import mqtt.pub.SensorManager;

public class MoistureManager extends SensorManager{	

	private int moisture;
	private final int DEFAULT_TEMP = 60;
	private final String TOPIC = TOPIC_UMIDADE;
	
	
	public MoistureManager() {
		super("Moisture_Client");
		// TODO Auto-generated constructor stub
		this.moisture = DEFAULT_TEMP;
	}



	public MoistureManager(String ip, String port, String clientId) {
		super(ip, port, clientId);
		this.moisture = DEFAULT_TEMP;
	}


	public MoistureManager(String ip, String port) {
		super(ip, port);
		this.moisture = DEFAULT_TEMP;
	}


	public void changeValue(int temp) {
		this.moisture = temp;
	}



	@Override
	public void update() {
		try {
			this.publicar(TOPIC, String.valueOf(moisture));
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public static void main(String args[]) {
		@SuppressWarnings("unused")
		MoistureManager moistureManager = new MoistureManager();
	}
	
}
