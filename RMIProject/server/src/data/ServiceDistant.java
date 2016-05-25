package data;

import api.Donnee;
import api.Service;

import java.rmi.RemoteException;

/**
 * Created by user on 25/05/16.
 */
public class ServiceDistant implements Service {


    @Override
    public String getInfo() throws RemoteException {
        return "A service that return a ResultServiceDistant object";
    }

    @Override
    public Donnee accesService() throws RemoteException {
        return new ResultServiceDistant();
    }
}
