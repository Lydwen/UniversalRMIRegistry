import data.Resultat;
import data.ServiceDistant;
import interfaces.Distante;
import interfaces.IUniversalRMIRegistry;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

/**
 * Created by dall'agnol on 20/05/16.
 */
public class Server {
    public static void main(String[] args){

        //System.setSecurityManager(new RMISecurityManager());


        try {

            //retrieve the registry
            IUniversalRMIRegistry reg = (IUniversalRMIRegistry) Naming.lookup("rmi://localhost:1098/universalrmiregistry");

            //put a data and a service into the registry
            Resultat result = new Resultat(21);
            reg.put("resultat", result);
            Distante service = new ServiceDistant();
            reg.put("Service", service);
        } catch (RemoteException | MalformedURLException | NotBoundException e) {
            e.printStackTrace();
        }

    }
}
