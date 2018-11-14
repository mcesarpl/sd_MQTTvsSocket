package mqtt.pub.sensors;

import org.eclipse.paho.client.mqttv3.MqttException;

import mqtt.pub.SensorManager;

public class PresenceManager extends SensorManager{	

	private boolean presence;
	private final boolean DEFAULT_PRESENCE = false;
	private final String TOPIC = TOPIC_PRESENCA;
	
	
	public PresenceManager() {
		super("Presence_Client");
		// TODO Auto-generated constructor stub
		this.presence = DEFAULT_PRESENCE;
	}



	public PresenceManager(String ip, String port, String clientId) {
		super(ip, port, clientId);
		this.presence = DEFAULT_PRESENCE;
	}


	public PresenceManager(String ip, String port) {
		super(ip, port);
		this.presence = DEFAULT_PRESENCE;
	}


	public void changeValue(boolean presence) {
		this.presence = presence;
	}



	@Override
	public void update() {
		try {
			this.publicar(TOPIC, String.valueOf(presence));
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public static void main(String args[]) {
		@SuppressWarnings("unused")
		PresenceManager presenceManager = new PresenceManager();
	}
	
}
