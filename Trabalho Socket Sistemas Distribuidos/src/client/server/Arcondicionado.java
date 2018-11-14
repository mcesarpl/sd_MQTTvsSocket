package client.server;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.Locale;

public class Arcondicionado
{
    private static boolean status = false;
    private static double temperatura;
    private static boolean presenca;
    
    public static void changeStatus(boolean newStatus){
        status = newStatus;
    }

    public static void changeTemp(double newTemperatura){
        temperatura = newTemperatura;
    }

    public static void changePresenca(boolean pres){
        presenca = pres;
    }
    
    public static boolean getStatus()
    {
    	return status;
    }

    public static void main(String arg[]){
        try{
            Socket socket = new Socket("127.0.0.1",3000);

            BufferedReader inFromServer = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
            );

            String[] value;
            while(true){
                value = inFromServer.readLine().split("_");
                switch(value[0]){
                    case "PRES": {
                        if(Boolean.parseBoolean(value[1])==true)
                        	changePresenca(true);
                        else changePresenca(false);
                        System.out.println("Changed to: " + value[1]);
                        break;
                    }
                    case "TEMP": {
                        changeTemp(new Double(value[1]));
                        System.out.println("Changed to: " + value[1]);
                        break;
                    }
                    default:
                        System.out.println("No change value.");
                }
                	System.out.println("PRESENÇA: "+presenca+" & TEMPERATURA:"+temperatura);
	                if(presenca==true && temperatura>25){
	                    changeStatus(true);
	                    System.out.println("Arcond is on!");
	                }else{
	                    changeStatus(false);
	                    System.out.println("Arcond is off!");
	                }
            }

        } catch(Exception e){
            System.out.println(e);
        }
    }
}
