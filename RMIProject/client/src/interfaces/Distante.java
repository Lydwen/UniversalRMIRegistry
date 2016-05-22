package interfaces;

import data.Resultat;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by dallagnol on 21/03/16.
 */
public interface Distante extends Remote {
    void echo() throws RemoteException;

    Resultat doSth(int param) throws RemoteException;
}
