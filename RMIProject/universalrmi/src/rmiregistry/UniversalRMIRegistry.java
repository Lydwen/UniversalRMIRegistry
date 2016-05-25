package rmiregistry;

import api.IUniversalRMIRegistry;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;
import java.util.Map;

/**
 * Implementation of a Universal RMI Registry
 * which can provide services and data
 *
 * Created by dallagnol on 20/05/16.
 */
public class UniversalRMIRegistry extends UnicastRemoteObject implements IUniversalRMIRegistry {
    //all the services and objects will be stored into storage
    private Map<String, Object> storage;

    public UniversalRMIRegistry() throws RemoteException {
        storage = new Hashtable<>();
    }

    @Override
    public Object get(String key) throws RemoteException {
        return storage.get(key);
    }

    @Override
    public void put(String key, Object obj) throws RemoteException {
        System.out.println("Put an object of the class " + obj.getClass());
        storage.put(key, obj);
    }

    @Override
    public void remove(String key) throws RemoteException {
        storage.remove(key);
    }

    @Override
    public boolean containsKey(String key) throws RemoteException {
        return storage.containsKey(key);
    }
}
