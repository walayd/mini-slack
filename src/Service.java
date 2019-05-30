import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.UUID;

public class Service extends UnicastRemoteObject implements IService {

    Authentification authInterface = null;
    protected Service(int port, Authentification auth) throws RemoteException {
        super(port);
        authInterface = auth;
    }

    @Override
    public String helloWorld(UUID token) throws RemoteException {
        if(authInterface.verifyAuth(token)!=null) {
            return "hello world";
        }else {
            return "authentification failed";
        }
    }
}
