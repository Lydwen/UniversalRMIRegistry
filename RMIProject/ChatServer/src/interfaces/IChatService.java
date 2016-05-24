package interfaces;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by user on 23/05/16.
 */
public interface IChatService  extends IService{
    long login(String name)  throws RemoteException;
    boolean sendMessage(long token, String message)  throws RemoteException ;
    String getQueue(long token)  throws RemoteException ;
}
