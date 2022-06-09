package tokyo.ramune.discord.command;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import javax.annotation.Nonnull;

public interface Command {

    @Nonnull
    String getCommandName();

    @Nonnull
    CommandTag getTag();

    @Nonnull
    String getDescription();

    void onCommand(@Nonnull String[] args, @Nonnull MessageReceivedEvent event);
}
