package client.server;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.Locale;

public class Luz
{
    private static boolean status = false;
    private static boolean presenca = false;
    private static double horario = 0.0;
    
    public static void changeStatus(boolean newStatus){
        status = newStatus;
    }

    public static void changePresenca(boolean pres){
        presenca = pres;
    }

    public static void changeHorario(double newTime){
        horario = newTime;
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
                        System.out.println("Changed to : " + value[1]);
                        break;
                    }
                    case "TIM": {
                        changeHorario((new Double(value[1])));
                        System.out.println("Changed to : " + value[1]);
                        if(horario>18 && presenca){
                            changeStatus(true);
                            System.out.println("Light is ON!");
                        }else{
                            changeStatus(false);
                            System.out.println("Light is OFF!");
                        }
                        break;
                    }
                    default:
                        System.out.println("No change value.");  
                }
                if((horario>18 || horario<5)&& presenca){
                    changeStatus(true);
                    System.out.println("Light is ON!");
                }else{
                    changeStatus(false);
                    System.out.println("Light is OFF!");
                }
            }

        } catch(Exception e){
            System.out.println(e);
        }
    }
}
