package chat.server;

import interfaces.IUniversalRMIRegistry;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

public class Main {

    public static void main(String[] args) {

      System.setSecurityManager(new RMISecurityManager());
        try {

            //retrieve the registry
            IUniversalRMIRegistry reg = (IUniversalRMIRegistry) Naming.lookup("rmi://localhost:1098/universalrmiregistry");
            ChatService service = new ChatService("salon");
            reg.put("chat",service);

        } catch (RemoteException | MalformedURLException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}
