package server.data;

public interface JsonDatabase {
    String get(String key);
    String set(String key, String value);
    String delete(String key);
}