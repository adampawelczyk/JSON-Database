package server;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class JsonDatabase {
    private final Path filePath;
    private final JsonObject database;

    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
    private final ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

    public JsonDatabase() throws IOException {
        filePath = Path.of("./db.json");
        createDatabaseIfNotExists();
        database = readDatabaseFromFile();
    }

    private void createDatabaseIfNotExists() throws IOException {
        if (!Files.exists(filePath)) {
            Files.writeString(filePath, "{}");
        }
    }

    private JsonObject readDatabaseFromFile() throws IOException {
        try (Reader reader = Files.newBufferedReader(filePath)) {
            return JsonParser.parseReader(reader).getAsJsonObject();
        }
    }

    private void writeDatabaseToFile() throws IOException {
        try (Writer writer = Files.newBufferedWriter(filePath)) {
            new Gson().toJson(database, writer);
        }
    }

    public String get(String key) {
        readLock.lock();
        String value = "No such key";
        try {
            if (database.has(key)) {
                value = database.get(key).getAsString();
            }
        } finally {
            readLock.unlock();
        }
        return value;
    }

    public void set(String key, String value) {
        writeLock.lock();
        try {
            database.addProperty(key, value);
            writeDatabaseToFile();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    public void delete(String key) {
        writeLock.lock();
        try {
            if (database.has(key)) {
                database.remove(key);
                writeDatabaseToFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    public boolean containsKey(String key) {
        return database.has(key);
    }
}
