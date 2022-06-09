package tokyo.ramune.discord.command;

import javax.annotation.Nonnull;
import java.util.ArrayList;

public class CommandHandler {

    private final static ArrayList<Command> commands = new ArrayList<>();

    public static void registerCommand(Command command) {
        commands.add(command);
    }

    public static Command[] getCommands() {
        return commands.toArray(new Command[0]);
    }

    public static Command[] getCommands(@Nonnull CommandTag tag) {
        ArrayList<Command> tagCommands = new ArrayList<>();
        for (Command command : commands) {
            if (command.getTag().equals(tag)) {
                tagCommands.add(command);
            }
        }
        return tagCommands.toArray(new Command[0]);
    }
}
