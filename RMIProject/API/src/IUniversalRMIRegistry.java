import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The interface to use our universal rmi registry
 *
 * Created by dall'agnol on 20/05/16.
 */
public interface IUniversalRMIRegistry extends Remote{

    /**
     * Get an object of the registry
     * thanks to a key linked to this object
     * @param key
     *          the key wich permits to access the object
     * @return the object referenced by the key
     * @throws RemoteException
     */
    Object get(String key) throws RemoteException;

    /**
     * Put an object into the registry
     * wich will be linked to the specified key
     * @param key
     *          the key which represents the object
     * @param obj
     *          the object we want to store
     * @throws RemoteException
     */
    void put(String key, Object obj) throws RemoteException;

    /**
     * Remove the object linked to the specified key
     *
     * @param key
     *          the key of the object we want to remove
     * @throws RemoteException
     */
    void remove(String key) throws RemoteException;

    /**
     * Check if the specified key exists in the registry
     * @param key
     *          the key we want to check
     * @return true if there is an object linked to this key, false otherwise
     * @throws RemoteException
     */
    boolean containsKey(String key) throws RemoteException;

}
