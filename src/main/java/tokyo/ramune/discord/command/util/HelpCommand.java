package tokyo.ramune.discord.command.util;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;
import tokyo.ramune.discord.command.Command;
import tokyo.ramune.discord.command.CommandHandler;
import tokyo.ramune.discord.command.CommandTag;

import java.awt.*;

public class HelpCommand implements Command {

    @NotNull
    @Override
    public String getCommandName() {
        return "help";
    }

    @NotNull
    @Override
    public CommandTag getTag() {
        return CommandTag.UTIL;
    }

    @NotNull
    @Override
    public String getDescription() {
        return "ヘルプを表示します";
    }

    @Override
    public void onCommand(@NotNull String @NotNull [] args, @NotNull MessageReceivedEvent event) {
        EmbedBuilder builder = new EmbedBuilder();

        builder.setTitle("ヘルプ")
                .setDescription("このbotの利用可能なコマンド一覧を表示します")
                .setColor(Color.YELLOW)
                .setAuthor(event.getJDA().getSelfUser().getName(), event.getJDA().getInviteUrl(Permission.ADMINISTRATOR), event.getJDA().getSelfUser().getAvatarUrl());

        for (CommandTag tag : CommandTag.values()) {
            StringBuilder commands = new StringBuilder();
            commands.append("コマンド -> 説明\n");
            for (Command command : CommandHandler.getCommands(tag)) {
                commands.append(command.getCommandName()).append(" -> ").append(command.getDescription()).append("\n");
            }
            builder.addField(tag.name().toLowerCase(), commands.toString(), false);
        }
        event.getChannel().sendMessageEmbeds(builder.build()).queue();
    }
}
