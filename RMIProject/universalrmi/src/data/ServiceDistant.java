package data;

import api.Donnee;
import api.Service;

import java.rmi.RemoteException;

/**
 * Created by user on 22/05/16.
 */
public class ServiceDistant implements Service {
    @Override
    public String getInfo() throws RemoteException {
        return "I'm a ServiceDistant and I return a Resultat object";
    }

    @Override
    public Donnee accesService() throws RemoteException {
        return new Resultat(12);
    }
}
