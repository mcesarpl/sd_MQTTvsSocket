package mqtt.pub;

import org.eclipse.paho.client.mqttv3.MqttException;

public abstract class SensorManager extends Publisher {

	protected static final long DELAY = 500;

	public SensorManager(String clientId) {
		super(clientId);
		monitore();
	}

	public SensorManager(String ip, String port, String clientId) {
		super(ip, port, clientId);
		monitore();
	}

	public SensorManager(String ip, String port) {
		super(ip, port);
		monitore();
	}

	public void monitore() {
		try {
			this.connect();

			new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {
						try {
							Thread.sleep(DELAY);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						update();
					}

				}
			}).start();
		} catch (MqttException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public abstract void update();
}
