import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONObject; //To use JSON
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class VaultManager {

    static class Colors {
        public static final String RESET = "\u001B[0m";
        public static final String RED = "\u001B[31m";
        public static final String GREEN = "\u001B[32m";
        public static final String BLUE = "\u001B[34m";
    }
    static String filename = "PasswordManager.json";


    public static JSONArray loadJson() throws IOException, ParseException {
        File f = new File(filename);
        if (!f.exists() || f.length() == 0) {
            return new JSONArray(); // Return empty array if file missing or empty
        }

        JSONParser parser = new JSONParser();
        return (JSONArray) parser.parse(new FileReader(filename));
    }

    public static void saveJson(JSONArray jsonArray) throws IOException {
        try (FileWriter file = new FileWriter(filename)) {
            file.write(jsonArray.toJSONString());
        }
    }


    // Add new credentials
    public static void add(String service, String username, String password) throws IOException, ParseException {
        JSONArray jsonArray = loadJson();

        // Check for duplicates
        for (Object o : jsonArray) {
            JSONObject obj = (JSONObject) o;
            String s = (String) obj.get("service");
            String u = (String) obj.get("username");

            if (s.equalsIgnoreCase(service) && u.equalsIgnoreCase(username)) {
                System.out.println(Colors.RED + "Credentials already exist for: " + service + Colors.RESET);
                return;
            }
        }

        // Add new entry
        JSONObject newEntry = new JSONObject();
        newEntry.put("service", service);
        newEntry.put("username", username);
        newEntry.put("password", password);

        jsonArray.add(newEntry);
        saveJson(jsonArray);

        System.out.println(Colors.GREEN + "Credentials added for: " + service + Colors.RESET);
    }

    // Delete credentials
    public static void delete(String service, String username) throws IOException, ParseException {
        JSONArray jsonArray = loadJson();
        boolean removed = false;

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject obj = (JSONObject) jsonArray.get(i);
            String s = (String) obj.get("service");
            String u = (String) obj.get("username");

            if (s.equalsIgnoreCase(service) && u.equalsIgnoreCase(username)) {
                jsonArray.remove(i);
                removed = true;
                break;
            }
        }

        if (removed) {
            saveJson(jsonArray);
            System.out.println(Colors.BLUE + "Deleted credentials for: " + service + Colors.RESET);
        } else {
            System.out.println(Colors.RED + "No credentials found for: " + service + Colors.RESET);
        }
    }

    // Get password
    public static void get(String service, String username) throws IOException, ParseException {
        JSONArray jsonArray = loadJson();

        for (Object o : jsonArray) {
            JSONObject obj = (JSONObject) o;
            String s = (String) obj.get("service");
            String u = (String) obj.get("username");

            if (s.equalsIgnoreCase(service) && u.equalsIgnoreCase(username)) {
                System.out.println("Password: " + obj.get("password"));
                return;
            }
        }

        System.out.println(Colors.RED + "No credentials found" + Colors.RESET);
    }

    // List all credentials
    public static List<Vault> list() throws IOException, ParseException {
        JSONArray jsonArray = loadJson();
        List<Vault> list = new ArrayList<>();

        if (jsonArray.isEmpty()) {
            System.out.println(Colors.RED + "No credentials stored" + Colors.RESET);
            return List.of();
        }


        for (Object o : jsonArray) {
            JSONObject obj = (JSONObject) o;
            list.add(new Vault((String) obj.get("service"), (String) obj.get("username"), (String) obj.get("password")));

        }

        return list;
    }
}