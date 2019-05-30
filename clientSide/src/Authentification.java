import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface Authentification extends Remote {
    UUID authentificate(String login, String password)throws RemoteException;

    IService getService(UUID token) throws RemoteException;
}
