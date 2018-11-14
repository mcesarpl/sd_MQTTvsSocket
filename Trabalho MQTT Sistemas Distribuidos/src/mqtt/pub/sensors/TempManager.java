package mqtt.pub.sensors;

import org.eclipse.paho.client.mqttv3.MqttException;

import mqtt.pub.SensorManager;

public class TempManager extends SensorManager{	

	private float temp;
	private final float DEFAULT_TEMP = 24.0f;
	private final String TOPIC = TOPIC_TEMPERATURA;
	
	
	public TempManager() {
		super("Temp_Client");
		// TODO Auto-generated constructor stub
		this.temp = DEFAULT_TEMP;
	}



	public TempManager(String ip, String port, String clientId) {
		super(ip, port, clientId);
		this.temp = DEFAULT_TEMP;
	}


	public TempManager(String ip, String port) {
		super(ip, port);
		this.temp = DEFAULT_TEMP;
	}


	public void changeValue(float temp) {
		this.temp = temp;
	}



	@Override
	public void update() {
		try {
			this.publicar(TOPIC, String.valueOf(temp));
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public static void main(String args[]) {
		@SuppressWarnings("unused")
		TempManager tempManager = new TempManager();
	}
	
}
