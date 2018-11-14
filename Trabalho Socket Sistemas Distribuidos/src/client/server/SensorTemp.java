package client.server;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.Locale;

public class SensorTemp
{
    private static double temp = 24.0;
    
    public static void changeTemp(double newTemp){
        temp = newTemp;
    }
    
    
    public static void main(String arg[]){
        try{
            Socket socket = new Socket("127.0.0.1",3000);

            DataOutputStream outToServer = new DataOutputStream(
                socket.getOutputStream()
            );

            while(true){
                outToServer.writeBytes(String.format(Locale.US, "TEMP_%.2f\n", temp));
                System.out.println("Changed to : " + temp);
                Thread.sleep(1000);
            }

        } catch(Exception e){
            System.out.println(e);
        }
    }
}
