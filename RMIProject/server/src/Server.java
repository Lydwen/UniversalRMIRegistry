import api.Donnee;
import api.IUniversalRMIRegistry;
import api.Service;
import data.Resultat;
import data.ServiceDistant;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
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
            Donnee result = new Resultat(21);
            reg.put("resultat", result);
            Service service = new ServiceDistant();
            reg.put("Service", service);
        } catch (RemoteException | MalformedURLException | NotBoundException e) {
            e.printStackTrace();
        }

    }
}
