package client;

import data.Resultat;
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
public class Client {
    public static void main(String[] args){
        System.setSecurityManager(new RMISecurityManager());
        try {

            //retrieve the registry
            IUniversalRMIRegistry reg = (IUniversalRMIRegistry) Naming.lookup("rmi://localhost:4242/universalrmiregistry");

            //get a service from the registry
            Distante service = (Distante)reg.get("Service");
            service.doSth(21);
            service.echo();

            //get a data from the registry
            Resultat res = (Resultat) reg.get("resultat");
            System.out.println(res.getAnInt());
        } catch (RemoteException | MalformedURLException | NotBoundException e) {
            e.printStackTrace();
        }

    }
}
