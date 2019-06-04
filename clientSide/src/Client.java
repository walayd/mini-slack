import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.UUID;

public class Client {

    private static ArrayList<String> commandList = new ArrayList<>();
    public static void main(String[] args) throws Exception {

        commandList.add("hello-world");
        commandList.add("help");
        commandList.add("quit");

        Registry reg = LocateRegistry.getRegistry(2001);
        Authentification mod = (Authentification)reg.lookup("AuthInterface");
        UUID token = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String login = "";
        while(token==null) {
            try {
                System.out.print("login : ");
                login = reader.readLine();
                System.out.print("password : ");
                String password = reader.readLine();


                token = mod.authentificate(login, password);
                System.out.println(token);
            } catch (Exception e) {
                System.out.println("authentication failed");
            }
        }
        System.out.println("welcome "+login);
        System.out.println("------------");

        String command = "";
        IService service = (IService) reg.lookup("Service");
        while(!command.equals("quit")){
            System.out.print(login + " > ");
            command = reader.readLine();
            switch (command) {
                case "hello-world":
                    try {
                        System.out.println(service.helloWorld(token));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "help":
                    System.out.println("the following commands are allowed : ");
                    for (String c : commandList) {
                        System.out.println("    -> " + c);
                    }
                    break;
                case "quit":
                    System.out.println("Thank you.\nGoodbye " + login + "!!");
                    break;
                default:
                    System.out.println("command " + command + " not recognize, use help to have a list of the commands");
                    break;
            }
        }
    }
}
