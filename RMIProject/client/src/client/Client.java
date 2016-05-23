package client;

import data.Resultat;
import interfaces.Distante;
import interfaces.IUniversalRMIRegistry;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.Hashtable;

/**
 * Created by dall'agnol on 20/05/16.
 */
public class Client {
    public static void main(String[] args){
        System.setSecurityManager(new RMISecurityManager());
        try {
            Hashtable env = new Hashtable();
            env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.rmi.registry.RegistryContextFactory");
            env.put(Context.PROVIDER_URL,"rmi://localhost:1098/");
            Context ctx = new InitialContext(env);
            //retrieve the registry
            IUniversalRMIRegistry reg = (IUniversalRMIRegistry) Naming.lookup("rmi://localhost:1098/universalrmiregistry");

            //get a service from the registry
            Distante service = (Distante)reg.get("Service");
            service.doSth(21);
            service.echo();

            //get a data from the registry
            Resultat res = (Resultat) reg.get("resultat");
            System.out.println(res.getAnInt());
        } catch (RemoteException | MalformedURLException | NotBoundException | NamingException e) {
            e.printStackTrace();
        }

    }
}
