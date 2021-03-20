package client;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

public class CommandParser {
    @Parameter(names={"-t", "--type"})
    private String type;
    @Parameter(names={"-k", "--key"})
    private String key;
    @Parameter(names={"-v", "--value"})
    private String value;

    public CommandParser(String[] args) {
        JCommander.newBuilder()
                .addObject(this)
                .build()
                .parse(args);
    }

    public String toJson() {
        Map<String, String> map = new HashMap<>();
        map.put("type", type);
        map.put("key", key);
        map.put("value", value);

        return new Gson().toJson(map);
    }
}