import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.UUID;

public class Client {

    public static void main(String[] args) throws Exception {
        Registry reg = LocateRegistry.getRegistry(2001);
        Authentification mod = (Authentification)reg.lookup("AuthInterface");
        UUID token = null;
        while(token==null) {
            try {
                System.out.print("login : ");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String login = reader.readLine();
                System.out.print("password : ");
                String password = reader.readLine();


                token = mod.authentificate(login, password);
                System.out.println(token);
            } catch (Exception e) {
                System.out.println("authentication failed");
            }
        }
        IService service = (IService) reg.lookup("Service");
        try{
            System.out.println(service.helloWorld(token));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
