package client.server;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;

public class MyClass extends Thread {

    private ServerSocket server;
    private Socket socket;
    //private static ArrayList<Socket> sockets = new ArrayList<Socket>();
    
    public MyClass(ServerSocket server, Socket socket){
        this.server = server;
        this.socket = socket;
    }

    public void run(){
    
        try{
            BufferedReader inFromClient = new BufferedReader(
                new InputStreamReader(this.socket.getInputStream())
            );
            DataOutputStream outToClient = new DataOutputStream(
                this.socket.getOutputStream()
            );
        
            outToClient.writeBytes("Connected!\n");
            while(true){
            	String[] value = inFromClient.readLine().split("_");
                switch(value[0]){
                    case "TEMP": {
                        //Server.changeTemp(new Double(value[1]));
                        System.out.println("MYCLASS Temperatura: "+ value[1]);
                        Server.sendValues(value[0] +"_"+ value[1]);
                        break;
                    }
                    case "HUM": {
                        //Server.changeHumidade(new Double(value[1]));
                    	System.out.println("MYCLASS Umidade: "+ value[1]);
                        Server.sendValues(value[0] +"_"+ value[1]);
                        break;
                    }
                    case "PRES": {
                        //Server.changePresenca(Boolean.getBoolean(value[1]));
                        System.out.println("MYCLASS Presenca: "+ value[1]);
                        Server.sendValues(value[0] +"_"+ value[1]);
                        break;
                    }
                    default:
                        Server.sendValues("Invalid Request!");
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }

    } 
}
