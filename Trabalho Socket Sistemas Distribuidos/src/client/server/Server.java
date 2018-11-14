package client.server;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Locale;

public class Server
{
    private static double temperatura;
    private static boolean presenca;
    private static double umidade;
    private static double relogio;
    private static ArrayList<Socket> sockets = new ArrayList<Socket>();

    public static void changeTemp(double newTemperatura){
        temperatura = newTemperatura;
        System.out.println("Temperatura aqui: "+temperatura);
        sendValues(String.format(Locale.US, "TEMP_%.2f\n", temperatura));
    }

    public static void changePresenca(boolean pres){
        presenca = pres;
        System.out.println("Presença aqui: "+presenca);
        sendValues("PRES_" + String.valueOf(presenca));
    }

    public static void changeHumidade(double hum){
        umidade = hum;
        System.out.println("Humidade aqui: "+umidade);
        sendValues(String.format(Locale.US, "HUM_%.2f\n", umidade));
    }

    public static void changeTime(double newTime){
        relogio = newTime;
        System.out.println("Tempo aqui: "+relogio);
        sendValues(String.format(Locale.US, "TIM_%.2f\n", relogio));
    }



    public static void sendValues(String msg){
        for(Socket socket : sockets){
        	try{
        		DataOutputStream outToClient = new DataOutputStream(
                    socket.getOutputStream()
                );
                outToClient.writeBytes(msg+"\n");
        	} catch(Exception e){
        		System.out.println("Exception: " + e);
        	}
    	}
    }

    public static void addClient(Socket socket){
        sockets.add(socket);
    }
    
    
    public static void showClients(){
    	System.out.println("List :");
    	for(Socket socket : sockets) {
    		System.out.println(socket);
    	}
    }

    public static void main(String arg[])
    {
        try{
            ServerSocket server = new ServerSocket(3000);
            System.out.println("Server is Online on port : 3000 ");
            
            
            while(true){
                 Socket client = server.accept();
                 addClient(client);
                 System.out.println("New Clien : " + client);
                 (new MyClass(server, client)).start();
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
