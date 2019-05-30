import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.UUID;

import static java.lang.Thread.sleep;

public class authImplem extends UnicastRemoteObject implements Authentification {

    private HashMap<UUID, String> usersAuth = new HashMap<>();
    private HashMap<String, Integer> userPassword = new HashMap<>();

    authImplem(int port) throws RemoteException {
        super(port);
        userPassword.put("login", "password".hashCode());
    }

    @Override
    public UUID authentificate(String login, String password) throws RemoteException {
        if(userPassword.get(login).equals(password.hashCode())) {
            UUID token = UUID.randomUUID();
            usersAuth.put(token, login);
            return token;
        }else{
            return UUID.fromString("");
        }
    }

    @Override
    public String verifyAuth(UUID token) throws RemoteException {
        return usersAuth.get(token);
    }
}
