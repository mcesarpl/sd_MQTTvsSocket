package mqtt.pub.sensors;

import org.eclipse.paho.client.mqttv3.MqttException;

import mqtt.pub.SensorManager;

public class ClockManager extends SensorManager{	

	private double time;
	private final double DEFAULT_TIME = 0.0;
	private final String TOPIC = TOPIC_RELOGIO;
	
	
	public ClockManager() {
		super("Clock_Client");
		// TODO Auto-generated constructor stub
		this.time = DEFAULT_TIME;
	}

	public ClockManager(String ip, String port, String clientId) {
		super(ip, port, clientId);
		this.time = DEFAULT_TIME;
	}


	public ClockManager(String ip, String port) {
		super(ip, port);
		this.time = DEFAULT_TIME;
	}


	public void changeValue(int hours, int minutes, int seconds) {
		this.time = hours + ((double)minutes)/60;
	}



	@Override
	public void update() {
		try {
//			this.time = new Date();
			this.publicar(TOPIC, String.valueOf(this.time));
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}	
	
	public static void main(String args[]) {
		@SuppressWarnings("unused")
		ClockManager tempManager = new ClockManager();
	}
	
}
