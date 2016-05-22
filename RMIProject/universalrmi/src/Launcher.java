import interfaces.IUniversalRMIRegistry;
import rmiregistry.UniversalRMIRegistry;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Created by dallagnol on 20/05/16.
 */
public class Launcher {
    public static void main(String[] args) {
        try {
            LocateRegistry.getRegistry(4242);
            IUniversalRMIRegistry registry = new UniversalRMIRegistry();

            Naming.rebind("rmi://localhost:4242/universalrmiregistry", registry);
        } catch (RemoteException e) {
            System.err.println("remote exception");
            e.printStackTrace();
        }catch (MalformedURLException e) {
            System.err.println("url fail");
            e.printStackTrace();
        }

    }
}
