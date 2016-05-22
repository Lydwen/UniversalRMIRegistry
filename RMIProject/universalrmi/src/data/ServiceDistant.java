package data;

import interfaces.Distante;

import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * Created by user on 22/05/16.
 */
public class ServiceDistant implements Distante, Serializable {
    @Override
    public void echo() throws RemoteException {
        System.out.println("Hi, I'm "+this.getClass()+" and I'm being created");
    }

    @Override
    public Resultat doSth(int param) throws RemoteException {
        System.out.println("Hi, I'm "+this.getClass()+" and I'm doing something");
        return new Resultat(param);
    }
}
