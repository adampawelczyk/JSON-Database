package server.data;

public interface JsonDatabase {
    String get(int index);
    String set(int index, String value);
    String delete(int index);
}