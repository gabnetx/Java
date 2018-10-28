package ejemploRMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    private Client() {}

    public static void main(String[] args) {
        String host="";
        int port=0;
        if(args.length == 2){
            host = args[0]; port = Integer.valueOf(args[1]);
        } else {System.err.println("Se requiere IP PUERTO");System.exit(1);}
        try {
            Registry registry = LocateRegistry.getRegistry(host,port);
            Hello stub = (Hello) registry.lookup("Hello");
            String response = stub.sayHello();
            System.out.println("response: " + response);
            response = stub.searchSerie("6671001");
            System.out.println("response: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
