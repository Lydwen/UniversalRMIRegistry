import interfaces.IUniversalRMIRegistry;
import rmiregistry.UniversalRMIRegistry;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Hashtable;

/**
 * Created by dallagnol on 20/05/16.
 */
public class Launcher {
    public static void main(String[] args) {
        System.setSecurityManager(new RMISecurityManager());
        try {
            Hashtable env = new Hashtable();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
            env.put(Context.PROVIDER_URL, "rmi://localhost:1098/");
            Context ctx = new InitialContext(env);
            LocateRegistry.getRegistry(1098);
            IUniversalRMIRegistry registry = new UniversalRMIRegistry();

            Naming.rebind("rmi://localhost:1098/universalrmiregistry", registry);
        } catch (RemoteException e) {
            System.err.println("remote exception");
            e.printStackTrace();
        }catch (MalformedURLException e) {
            System.err.println("url fail");
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }

    }
}
