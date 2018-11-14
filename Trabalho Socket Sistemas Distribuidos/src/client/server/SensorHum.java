package client.server;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.Locale;

public class SensorHum
{
    private static double humidade = 60;
    
    public static void changeHumidade(double newHum){
        humidade = newHum;
    }

    public static void main(String arg[]){
        try{
            Socket socket = new Socket("127.0.0.1",3000);

            DataOutputStream outToServer = new DataOutputStream(
                socket.getOutputStream()
            );

            while(true){
            	System.out.println("Umidade no sensor: "+ humidade);
                outToServer.writeBytes(String.format(Locale.US, "HUM_%.2f\n", humidade));
                System.out.println("Umidade no sensor lida do server: "+ humidade);

                Thread.sleep(1200);
            }

        } catch(Exception e){
            System.out.println(e);
        }
    }
}
