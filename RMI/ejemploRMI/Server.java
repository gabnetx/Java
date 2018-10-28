package ejemploRMI;

import java.io.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class Server implements Hello {

    HashMap<String,String> map = new HashMap<>();
    String HOST="";

    public Server() {
        String line;
        String[] values;
        try{
            Process process = Runtime.getRuntime().exec("hostname");
            BufferedReader  buffer = new BufferedReader(new InputStreamReader ( process.getInputStream() ) );
            if( (line = buffer.readLine()) != null){
                HOST = line;
            }
            process = Runtime.getRuntime().exec("cat /home/pi/RMI/infoToLoad.dat");
            buffer = new BufferedReader(new InputStreamReader ( process.getInputStream() ) );
            while( (line = buffer.readLine()) != null){
                values = line.split("\\|");
                map.put( values[2],values[0]+","+values[4]+" "+values[4]);
            }            
        }catch(IOException ex){
            ex.getMessage();
        }    
    }

    public String sayHello() {
        return "Hello from "+HOST;
    }

    public String searchSerie( String serie){
        if(map.containsKey(serie))
            return map.get(serie);
        return "No existe informacion";
    }

    public static void main(String args[]) {

        if(args.length > 0){
                System.out.println("Se iniciara RMI en el puerto "+args[0]);
        } else { 
            System.err.println("Se requiere un puerto");
            System.exit(1);
        }

        try {
            //Create and export a remote object
            Server obj = new Server();
            Hello stub = (Hello) UnicastRemoteObject.exportObject(obj, 0);
            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry(Integer.valueOf(args[0]));
            registry.bind("Hello", stub);
            System.out.println("Server registry and ready");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}