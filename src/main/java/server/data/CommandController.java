package server.data;

public class CommandController {
    private final JsonDatabase jsonDatabase;
    private String type;
    private int index;
    private String text;


    public CommandController(JsonDatabase jsonDatabase) {
        this.jsonDatabase = jsonDatabase;
    }

    public void parseCommand(String command) {
        String[] commandElements = command.split(" ", 3);
        type = commandElements[0];
        index = Integer.parseInt(commandElements[1]) - 1;
        text = commandElements.length > 2 ? commandElements[2] : "";
    }

    public String executeCommand(String command) {
        parseCommand(command);

        switch (type) {
            case "get" -> {
                return jsonDatabase.get(index);
            }
            case "set" -> {
                return jsonDatabase.set(index, text);
            }
            case "delete" -> {
                return jsonDatabase.delete(index);
            }
            default -> {
                return "ERROR";
            }
        }
    }
}