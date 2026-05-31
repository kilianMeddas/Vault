import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONObject; //To use JSON
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class VaultManager {
    static List<Vault> vaults = new ArrayList<>();
    static class Colors {
        public static final String RESET = "\u001B[0m";

        public static final String RED = "\u001B[31m";
        public static final String GREEN = "\u001B[32m";
        public static final String BLUE = "\u001B[34m";
    }
    static String filename = "PasswordManager.json";


    public static JSONArray loadJson() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(filename));
        return (JSONArray) obj;
    }

    public static void saveJson(JSONArray jsonArray) throws IOException {
        FileWriter file = new FileWriter(filename);
        file.write(jsonArray.toJSONString()); //What we want to write in it, it's write-in the RAM
        // file.flush(); // Say "Write now" (if close just after, it's not necessary)
        file.close(); // Write and close (do auto flush)
    }



    private static List<Task> loadTasksFromJson() throws IOException, ParseException {
        JSONArray jsonArray= loadJson();
        List<Task> list = new ArrayList<>();

        for (Object o : jsonArray) {
            Long id = (Long) ((JSONObject) o).get("id");
            String TaskName = (String) ((JSONObject) o).get("Task");
            list.add(new Task(id, TaskName));
        }
        return list;
    }

    public static void add(String service, String username, String password) throws IOException, ParseException {
        boolean already_used = false;
        boolean to_add = false;

        if (!vaults.isEmpty()){

            for (Vault v : vaults){
                String serviceAndUsername = v.service.toLowerCase() + v.username.toLowerCase();
                if (serviceAndUsername.equals(service.toLowerCase()+username.toLowerCase())){
                    System.out.print(Colors.RED + "Credentials already added for : "+ service + Colors.RESET+"\n");
                    already_used = true;
                    break;
                }
            }
            if( !already_used){
                Vault vault = new Vault(service, username, password);
                vaults.add(vault);
                to_add = true;
            }
        } else{
            Vault vault = new Vault(service, username, password);
            vaults.add(vault);
            to_add = true;

        }

        if (to_add){
            File f = new File(filename);
            JSONObject jsonFile = new JSONObject();
            jsonFile.put("service", service);
            jsonFile.put("username", username);
            jsonFile.put("password", password);

            if (!f.exists() || f.length()==0){
                JSONArray jsonArray = new JSONArray();
                jsonArray.add(jsonFile);
                saveJson(jsonArray);

            } else{
                JSONArray jsonArray = loadJson();
                jsonArray.add(jsonFile);
                saveJson(jsonArray);

            }
        }


    }

    public static void delete(String service, String username) throws IOException, ParseException {
        JSONArray jsonArray = loadJson();
        for (int i = 0; i < jsonArray.size(); i++){
            String service_i = (String)((JSONObject)jsonArray.get(i)).get("service");
            String username_i = (String)((JSONObject)jsonArray.get(i)).get("username");
            String serviceAndUsername = service_i + username_i;
            if (serviceAndUsername.equals(service+username)){
                jsonArray.remove(i);
                break;
            }
        }
        saveJson(jsonArray);

    }
    public static void main(String[] args) throws IOException, ParseException {
        add("eee", "username", "password");
        add("service", "username", "password");
        add("service", "username", "password");

        for (Vault v :  vaults){
            System.out.println(v.service);
        }
    }
}
