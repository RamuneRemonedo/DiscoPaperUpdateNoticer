package tokyo.ramune.discord.listener;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import tokyo.ramune.Main;
import tokyo.ramune.config.Config;
import tokyo.ramune.discord.command.Command;
import tokyo.ramune.discord.command.CommandHandler;

public class CommandListener extends ListenerAdapter {

    private static final Config config = Main.getConfig();

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }
        if (!event.getMessage().getContentRaw().startsWith(config.getPrefix())) {
            return;
        }

        String commandName = event.getMessage().getContentRaw()
                .replace(config.getPrefix(), "").split(" ")[0];
        String[] args = event.getMessage().getContentRaw()
                .replace(config.getPrefix(), "")
                .replace(commandName + "", "")
                .replace(commandName + " ", "")
                .split(" ");

        for (Command command : CommandHandler.getCommands()) {
            if (commandName.equals(command.getCommandName())) {
                command.onCommand(args, event);
            }
        }
    }
}
