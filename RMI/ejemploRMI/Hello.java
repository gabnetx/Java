package ejemploRMI;
 
import java.rmi.Remote;
import java.rmi.RemoteException;
public interface Hello extends Remote {
    String sayHello() throws RemoteException;
    String searchSerie(String serie) throws RemoteException;
}
