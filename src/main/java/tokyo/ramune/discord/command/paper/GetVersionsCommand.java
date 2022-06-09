package tokyo.ramune.discord.command.paper;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;
import tokyo.ramune.Main;
import tokyo.ramune.discord.command.Command;
import tokyo.ramune.discord.command.CommandTag;
import tokyo.ramune.papermc.PaperMCAPIHandler;
import tokyo.ramune.util.Chat;

import java.util.Arrays;

public class GetVersionsCommand implements Command {
    @NotNull
    @Override
    public String getCommandName() {
        return "versions";
    }

    @NotNull
    @Override
    public CommandTag getTag() {
        return CommandTag.PAPER;
    }

    @NotNull
    @Override
    public String getDescription() {
        return "バージョンを取得します";
    }

    @Override
    public void onCommand(@NotNull String[] args, @NotNull MessageReceivedEvent event) {
        if (args.length == 1) {
            Chat.error(event.getChannel(), "引数エラー", "コマンド例: " + Main.getConfig().getPrefix() + "versions (プロジェクト名)");
            return;
        }
        try {
            String versions = "";
            int i = 1;
            for (String version : PaperMCAPIHandler.getVersions(args[1])) {
                versions = versions + "・" + version + "  ";
                if (i % 7 == 0) {
                    versions = versions + "\n";
                }
                i++;
            }
            Chat.success(event.getChannel(), "現在のバージョン一覧", versions);
        } catch (Exception e) {
            Chat.error(event.getChannel(), "バージョンの取得に失敗しました", Arrays.toString(e.getStackTrace()));
        }
    }
}
