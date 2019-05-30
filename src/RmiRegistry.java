import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RmiRegistry {

    public static void main(String[] args){
        try{
            Authentification stubAuth = new authImplem(2002);
            IService stubService = new Service(2003, stubAuth);
            Registry reg = LocateRegistry.createRegistry(2001);
            reg.rebind("AuthInterface", stubAuth);
            reg.rebind("Service", stubService);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
