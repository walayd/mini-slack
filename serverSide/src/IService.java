import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface IService  extends Remote, Serializable {
    String helloWorld(UUID token) throws RemoteException;
}
