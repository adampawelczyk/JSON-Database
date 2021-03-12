package server.data;

import java.util.HashMap;
import java.util.Map;

public class JsonDatabaseMap implements JsonDatabase {
    private final Map<String, String> jsonDatabase;

    public JsonDatabaseMap() {
        jsonDatabase = new HashMap<>();
    }

    @Override
    public String get(String key) {
        return jsonDatabase.getOrDefault(key, "No such key");
    }

    @Override
    public String set(String key, String value) {
        jsonDatabase.put(key, value);
        return "OK";
    }

    @Override
    public String delete(String key) {
        jsonDatabase.remove(key);
        return "OK";
    }

    public boolean containsKey(String key) {
        return jsonDatabase.containsKey(key);
    }
}