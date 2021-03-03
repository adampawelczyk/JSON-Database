package server.data;

public class JsonDatabaseArray implements JsonDatabase {
    private final String[] jsonDatabase;
    private final int size;

    public JsonDatabaseArray(int size) {
        this.jsonDatabase = new String[size];
        this.size = size;
    }

    @Override
    public String get(int index) {
        if (index < 0 || index > size || jsonDatabase[index] == null) {
            return "ERROR";
        } else {
            return jsonDatabase[index];
        }
    }

    @Override
    public String set(int index, String value) {
        if (index < 0 || index > size) {
            return "ERROR";
        } else {
            jsonDatabase[index] = value;
            return "OK";
        }
    }

    @Override
    public String delete(int index) {
        if (index < 0 || index >= size) {
            return "ERROR";
        } else {
            jsonDatabase[index] = null;
            return "OK";
        }
    }
}