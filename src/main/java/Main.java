import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    static class Colors {
        public static final String RESET = "\u001B[0m";
        public static final String RED = "\u001B[31m";
        public static final String GREEN = "\u001B[32m";
        public static final String BLUE = "\u001B[34m";
    }
    public static void main(String[] args) throws IOException, ParseException {
        if (args.length == 0) {
            System.out.println( Colors.RED+ "No command provided"+ Colors.RESET);
            return;
        }
        String command = args[0];
        switch (command){
            case("add")->{
                switch(args.length){
                    case(1)->{
                        System.out.println(Colors.RED+"No service, username and password provided"+Colors.RESET);
                        return;
                    }
                    case(2)->{
                        System.out.println(Colors.RED+"No username and password provided"+Colors.RESET);
                        return;
                    }
                    case(3)->{
                        System.out.println(Colors.RED+"No password provided"+Colors.RESET);
                        return;
                    }
                }

                String service = args[1];
                String username = args[2];
                String password = args[3];
                VaultManager.add(service, username, password);
            }
            case("delete")->{
                switch(args.length){
                    case(1)->{
                        System.out.println(Colors.RED+"No service and username provided"+Colors.RESET);
                        return;
                    }
                    case(2)->{
                        System.out.println(Colors.RED+"No username provided"+Colors.RESET);
                        return;
                    }
                }
                String service = args[1];
                String username = args[2];
                VaultManager.delete(service, username);
            }
            case("get")->{
                switch(args.length){
                    case(1)->{
                        System.out.println(Colors.RED+"No service and username provided"+Colors.RESET);
                        return;
                    }
                    case(2)->{
                        System.out.println(Colors.RED+"No username provided"+Colors.RESET);
                        return;
                    }
                }
                String service = args[1];
                String username = args[2];
                VaultManager.get(service, username);
            }
            case("list")->{
                String service = "";
                if (!(args.length == 1)) {
                    service = args[1];

                }
                List<Vault> list = VaultManager.list();

                if (service.isBlank()){
                    HashMap<String, List<String>> listHashMap= new HashMap<>();

                    for (Vault v : list) {

                        listHashMap.computeIfAbsent(
                                v.service,
                                k -> new ArrayList<>()
                        );

                        listHashMap.get(v.service)
                                .add("Username: " + v.username);
                    }

                    for (String serviceName : listHashMap.keySet()) {

                        System.out.println("Service: " + serviceName);

                        for (String info : listHashMap.get(serviceName)) {
                            System.out.println("    " + info);
                        }

                        System.out.println();
                    }
                } else{
                    for (Vault v : list) {
                        if (v.service.equals(service)){
                            System.out.println("Username: " +v.username);
                        }
                    }

                }
            }
            case("--help") -> {
                System.out.println("""
        Password Vault CLI

        Usage:
          password <command> [arguments]

        Commands:

          add <service> <username> <password>
              Add a new credential entry.

              Example:
                password add github johndoe myPassword123

          get <service> <username>
              Retrieve credentials for a specific service and username.

              Example:
                password get github johndoe

          delete <service> <username>
              Delete a credential entry.

              Example:
                password delete github johndoe

          list
              List all stored services and usernames.

              Example:
                password list

          list <service>
              List all usernames and passwords for a specific service.

              Example:
                password list github

          --help
              Display this help message.

        Notes:
          - Service names are case-sensitive.
          - Store passwords securely and avoid exposing them in shell history.
        """);
            }
        }
    }
}
