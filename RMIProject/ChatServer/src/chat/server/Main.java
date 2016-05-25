package chat.server;


import api.IUniversalRMIRegistry;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.Hashtable;

public class Main {

    public static void main(String[] args) {

        System.setSecurityManager(new RMISecurityManager());
        try {
            Hashtable env = new Hashtable();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
            env.put(Context.PROVIDER_URL, "rmi://localhost:1098/");
            Context ctx = new InitialContext(env);
            //retrieve the registry
            IUniversalRMIRegistry reg = (IUniversalRMIRegistry) Naming.lookup("rmi://localhost:1098/universalrmiregistry");
            ChatService service = new ChatService("salon");
            reg.put("chat",service);

        } catch (RemoteException | MalformedURLException | NotBoundException | NamingException e) {
            e.printStackTrace();
        }
    }
}
