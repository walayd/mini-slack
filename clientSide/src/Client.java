import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.UUID;

public class Client {

    public static void main(String[] args) throws Exception {
        Registry reg = LocateRegistry.getRegistry(2001);
        Authentification mod = (Authentification)reg.lookup("AuthInterface");
        UUID token = null;
        try {
            token = mod.authentificate("login","password");
            System.out.println(token);
        }catch(Exception e){
            System.out.println("authentification failed");
        }
        IService service = (IService) reg.lookup("Service");
        try{
            System.out.println(service.helloWorld(token));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
