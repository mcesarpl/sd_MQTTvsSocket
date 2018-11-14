package mqtt.sub;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;

public abstract class EquipmentManager extends Subscriber {
	private static final String DEFAULT_CLIENT_ID = "Equipamento";
	
	protected boolean status;
	
	public EquipmentManager(String clientId) {
		super(clientId);
		this.status = false;
	}

	public EquipmentManager(String ip, String port, String clientId) {
		super(ip, port, clientId);
		this.status = false;
	}

	public EquipmentManager(String ip, String port) {
		super(ip, port, DEFAULT_CLIENT_ID);
		this.status = false;
	}	
	
	@Override
	public void connectionLost(Throwable arg0) {
		System.out.println("Perdeu a conexão.");
		
	}
			
	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {
		//TODO
	}
	
}
