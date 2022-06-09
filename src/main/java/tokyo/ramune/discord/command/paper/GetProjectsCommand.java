package tokyo.ramune.discord.command.paper;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;
import tokyo.ramune.discord.command.Command;
import tokyo.ramune.discord.command.CommandTag;
import tokyo.ramune.papermc.PaperMCAPIHandler;
import tokyo.ramune.util.Chat;

import java.util.Arrays;

public class GetProjectsCommand implements Command {
    @NotNull
    @Override
    public String getCommandName() {
        return "projects";
    }

    @NotNull
    @Override
    public CommandTag getTag() {
        return CommandTag.PAPER;
    }

    @NotNull
    @Override
    public String getDescription() {
        return "プロジェクトを取得します";
    }

    @Override
    public void onCommand(@NotNull String[] args, @NotNull MessageReceivedEvent event) {
        try {
            String projects = "";
            for (String project : PaperMCAPIHandler.getProjects()) {
                projects = projects + "\n・" + project;
            }
            Chat.success(event.getChannel(), "現在のプロジェクト一覧", projects);
        } catch (Exception e) {
            Chat.error(event.getChannel(), "プロジェクトの取得に失敗しました", Arrays.toString(e.getStackTrace()));
        }
    }
}
