package client.server;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.Locale;

public class Irrigador
{
    private static boolean status = false;
    private static double umidade = 30;
    
    public static void changeStatus(boolean newStatus){
        status = newStatus;
    }

    public static void changeHumidade(double hum){
        umidade = hum;
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
                    case "HUM": {
                        changeHumidade(new Double(value[1]));
                        System.out.println("Changed to : " + value[1]);
                        if(umidade<30){
                            changeStatus(true);
                            System.out.println("irrigator is ON!");
                        }else{
                            changeStatus(false);
                            System.out.println("irrigator is OFF!");
                        }
                        break;
                    }
                    default:
                        System.out.println("No change value.");
                }
            }

        } catch(Exception e){
            System.out.println(e);
        }
    }
}
