package server.data;

import com.google.gson.Gson;
import java.util.LinkedHashMap;
import java.util.Map;

public class CommandController {
    private final JsonDatabaseMap jsonDatabase;

    public CommandController(JsonDatabaseMap jsonDatabase) {
        this.jsonDatabase = jsonDatabase;
    }

    public String executeCommand(Command command) {
        Map<String, String> response = new LinkedHashMap<>();

        switch (command.getType()) {
            case "get" -> {
                if (jsonDatabase.get(command.getKey()).equals("No such key")) {
                    response.put("response", "ERROR");
                    response.put("reason", "No such key");
                } else {
                    response.put("response", "OK");
                    response.put("value", jsonDatabase.get(command.getKey()));
                }
                return new Gson().toJson(response);
            }
            case "set" -> {
                jsonDatabase.set(command.getKey(), command.getValue());
                response.put("response", "OK");
                return new Gson().toJson(response);
            }
            case "delete" -> {
                if (jsonDatabase.containsKey(command.getKey())) {
                    jsonDatabase.delete(command.getKey());
                    response.put("response", "OK");
                } else {
                    response.put("response", "ERROR");
                    response.put("reason", "No such key");
                }
                return new Gson().toJson(response);
            }
            default -> {
                response.put("response", "ERROR");
                response.put("reason", "Invalid type");
                return new Gson().toJson(response);
            }
        }
    }
}