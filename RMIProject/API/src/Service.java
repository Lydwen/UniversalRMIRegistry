
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The interface to use a Service
 * Created by dallagnol on 21/03/16.
 */
public interface Service extends Remote {

    /**
     * Get tthe infos on the Service
     * @return a String which describes the Service
     * @throws RemoteException
     */
    String getInfo() throws RemoteException;

    /**
     * Use the Service and return the Resultat of its execution
     * @return the result object of the execution of the Service
     * @throws RemoteException
     */
    Donnee accesService() throws RemoteException;
}
