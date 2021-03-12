package server.data;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CommandParser {
    public static Command parse(String jsonCommand) {
        JsonObject jsonObject = JsonParser.parseString(jsonCommand).getAsJsonObject();
        Gson gson = new Gson();
        return gson.fromJson(jsonObject, Command.class);
    }
}